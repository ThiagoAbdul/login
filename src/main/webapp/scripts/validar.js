export const regex = {
    CARACTER_MINUSCULO: /[a-z]/,
    CARACTER_MAIUSCULO: /[A-Z]/,
    CARACTER_ESPECIAL: /[!@#$%&]/,
    CARACTER_NUMERICO: /[0-9]/,
    DOZE_CARACTERES: /.{12,}/,
    EMAIL: /^[a-zA-Z0-9_.]+@[\w]+([.][\w]+)+$/
}
export const emailInvalido = (email) => {
    if(email.match(regex['EMAIL'])){
        return false
    }
    return true
}

export const senhaPossui = (senha, regex) => {
    if(senha.match(regex)) {
        return true
    }
    return false
}

export const senhaNaoPossui = (senha, regex) => senhaPossui(senha, regex) === false

export const isSenhaFraca = (senha) => {
    return  senhaNaoPossui(senha, regex['CARACTER_MINUSCULO']) |
            senhaNaoPossui(senha, regex['CARACTER_MAIUSCULO']) |
            senhaNaoPossui(senha, regex['CARACTER_ESPECIAL']) |
            senhaNaoPossui(senha, regex['CARACTER_NUMERICO']) |
            senhaNaoPossui(senha, regex['DOZE_CARACTERES'])
}