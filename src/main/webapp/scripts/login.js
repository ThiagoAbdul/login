import { emailInvalido, senhaFraca } from './validar.js'

const btnEntrar = document.querySelector("#btn-entrar")

const getEmail = () => loginEmail.value
const getSenha = () => loginSenha.value


const logar = () => {
    if(emailInvalido(getEmail())){
        alert("Favor preencher seu e-mail corretamente")
        loginEmail.focus()
    }
    else if(senhaFraca(getSenha())){
        alert("Senha não atende os critérios")
        loginSenha.focus()
    }
    else {
        formLogin.submit()
    }
}

loginEmail.addEventListener('keyup', e => {
    if(e.key === 'Enter'){
        if(emailInvalido(getEmail())){
            alert("Favor preencher seu e-mail corretamente")
        }
        else{
            loginSenha.focus()
        }
    } 
})

loginSenha.addEventListener('keyup', e => {
    if(e.key === "Enter") logar()
})

btnEntrar.addEventListener('click', logar)