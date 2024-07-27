
class TranslatorTemplate:
    def __init__(self):
        self.initialized = False
        self.input_lang = None
        self.output_lang = None

    def build(self,input_language, output_language, text):
        if(self.input_lang != input_language or self.output_lang != output_language or not self.initialized):
            self.initialized = False
            return self.__initial_prompt__(input_language, output_language, text)
        else:
            return self.__has_memory_prompt__(input_language, output_language, text)



    def __initial_prompt__(self, input_language, output_language, text):
        self.initialized = True
        self.input_lang, self.output_lang = input_language, output_language

        prompt = f'''Você é um assistente que tem o papel apenas de fazer traduções de texto. 
            Não retorne nada além do texto traduzido, sem outras falas desnecessárias, exceto
            se "{input_language}" ou "{output_language}" não forem idiomas válidos, diga: "Não conheço uma das linguagens".
            Traduza do {input_language} para o {output_language}: "{text}"
        '''
        return prompt

    def __has_memory_prompt__(self, input_language, output_language, text):
        prompt = f'Traduza: "{text}"'
        return prompt
