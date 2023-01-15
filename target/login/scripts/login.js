import { emailInvalido, isSenhaFraca } from './validar.js'

const btnEntrar = document.querySelector("#btn-entrar")

const getEmail = () => document.querySelector("#login-email").value
const getSenha = () => document.querySelector("#login-senha").value


btnEntrar.addEventListener('click', () => {
    if(emailInvalido(getEmail())){
        alert("Favor preencher seu e-mail corretamente")
    }
    else if(isSenhaFraca(getSenha())){
        alert("Senha não atende os critérios")
    }
    else {
        formLogin.submit()
    }
})