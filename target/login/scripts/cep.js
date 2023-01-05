const formatarCep = (cep) => {
    if(cep.trim().match(/^\d{5}[-]\d{3}$/)){
        return cep.trim().replace("-", "")
    }
    return cep.trim()   
}

const getCep = () => formatarCep(cep.value)

const getUrl = () => {
    const cep = getCep()
    return `https://viacep.com.br/ws/${cep}/json`
}

const getInput = (id) => document.querySelector('#' + id)

const setReadOnly = (input, value) => input.setAttribute('readonly', value)

const getInputs = (...id) => id.map(getInput)

const limparCampos = () => {
    const campos = getInputs('logradouro', 'localidade', 'uf')
    campos.forEach(e => {
        setReadOnly(e, false)
        e.value = ""
    })
}

const isCampoValido = (campo) => ['logradouro', 'localidade', 'uf'].includes(campo)

const preencherCampos = (json) => {
    cep.value = getCep()
    let input
    for (campo in json){
        if(isCampoValido(campo)){ 
            input = getInput(campo)
            input.value = json[campo]
            setReadOnly(input, true)
        }
    }
}

const preencheuCep = () => getCep() !== ""

const responderErro = () => {
    alert('Erro na requisição')
    limparCampos()
}

const fazerRequest = (tarefa) => {
    const options = {
        method: 'GET',
        mode: 'cors',
        cache: 'default'
    }
    const url = getUrl()
    fetch(url, options)
        .then(e => e.json()
            .then(tarefa)
            .catch(responderErro)
        )
        .catch(responderErro)
}


cep.addEventListener('blur', () => {
    if(preencheuCep()){
        fazerRequest(preencherCampos)
    }
    else{
        limparCampos()
    }
})


