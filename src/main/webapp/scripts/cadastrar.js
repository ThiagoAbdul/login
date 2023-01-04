const getEmail = () => cadastroEmail.value
const getSenha = () => cadastroSenha.value
const getConfirmarSenha = () => confirmarSenha.value

const emailInvalido = () => {
    const email = getEmail()
    if(email.match(/^[a-zA-Z0-9_.]+@[\w]+([.][\w]+)+$/)){
        return false
    }
    return true
}

const senhaNaoPossui = (regex) => {
    const senha = getSenha()
    if(senha.match(regex)) {
        return false
    }
    return true
}

const isSenhaFraca = () => {
    return  senhaNaoPossui(/[a-z]/) |
            senhaNaoPossui(/[A-Z]/) |
            senhaNaoPossui(/[0-9]/) |
            senhaNaoPossui(/[!@#$%&]/) |
            senhaNaoPossui(/.{12,}/)
}

const senhasDiferentes = () => getSenha() !== getConfirmarSenha()

btnCadastrar.addEventListener('click', () => {
    if(emailInvalido()){
        alert('Favor, preencher o e-mail corretamente.')
    }
    else if(isSenhaFraca()){
        alert('Senha não atende os requisitos.')
    }
    else if(senhasDiferentes()){
        alert('Senhas não coincidem.')
    }
    else{
        formCadastrar.submit()
    } 
})
