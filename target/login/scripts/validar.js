export const emailInvalido = (email) => {
    if(email.match(/^[a-zA-Z0-9_.]+@[\w]+([.][\w]+)+$/)){
        return false
    }
    return true
}

const senhaNaoPossui = (senha, regex) => {
    if(senha.match(regex)) {
        return false
    }
    return true
}

export const isSenhaFraca = (senha) => {
    return  senhaNaoPossui(senha, /[a-z]/) |
            senhaNaoPossui(senha, /[A-Z]/) |
            senhaNaoPossui(senha, /[0-9]/) |
            senhaNaoPossui(senha, /[!@#$%&]/) |
            senhaNaoPossui(senha, /.{12,}/)
}