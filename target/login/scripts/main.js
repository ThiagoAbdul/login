const btnEntrar = document.querySelector("#btn-entrar")

const getEmail = () => document.querySelector("#login-email").value
const getSenha = () => document.querySelector("#login-senha").value

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

btnEntrar.addEventListener('click', () => {
    if(emailInvalido()){
        alert('Favor, preencher o e-mail corretamente.')
    }
    else if(isSenhaFraca()){
        alert('Senha não atende os requisitos.')
    }
    else{
        formLogin.submit()
    } 
})
