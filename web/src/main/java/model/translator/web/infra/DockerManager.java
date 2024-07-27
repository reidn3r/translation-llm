package model.translator.web.infra;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.translator.web.DTO.ContainerResponseDTO;

@Service
public class DockerManager {
    public String containerId;
    public boolean initialized = false;
    private String imageName = "llm_translator:1.0";
    private ObjectMapper objectMapper = new ObjectMapper();

    
    public DockerManager() throws Exception{
        this.createContainer();
    }

    private void createContainer() throws Exception{
        try{
            Process process = new ProcessBuilder("docker", "run", "-d", "-p", "2703:2703", this.imageName).start();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            this.containerId = reader.readLine();
            this.initialized = true;
            
            reader.close();
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public ContainerResponseDTO run(String input_lang, String output_lang, String text) throws Exception{
        String path = "/llm/v1_translator/src/main.py";
        
        Process process = new ProcessBuilder("docker", "run", this.imageName, "python3", path, input_lang, output_lang, text).start();        
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String containerOutput = reader.readLine();
        
        ContainerResponseDTO output = this.string2dto(containerOutput);
        return output;
    }

    private ContainerResponseDTO string2dto(String data) throws Exception{
        try{
            String formattedData = data.replaceAll("(?<=:)\\s*\"([^\"]*)\"(?=\\s*[,}])", "\"$1\"");
            ContainerResponseDTO output = this.objectMapper.readValue(formattedData, ContainerResponseDTO.class);
            return output;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
