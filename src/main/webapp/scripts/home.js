const uploadImagem = document.querySelector("#upload-imagem")
const texto = document.querySelector("#texto")
 
const mostrarBotes = () => {
    fundo.style.backgroundColor = 'rgba(0, 0, 0, 0.25)'
    document.querySelector("main").classList.add('fundo-foto')
    document.querySelectorAll(".botao").forEach(e => {
        e.style.display = 'block'
    })
}

const ocultarBotoes = () => {
    fundo.style.backgroundColor = 'inherit'
    document.querySelector("main").classList.remove('fundo-foto')
    document.querySelectorAll(".botao").forEach(e => {
        e.style.display = 'none'
    })
}

btnDescartarFoto.addEventListener('click', () => {
    document.querySelector("#container-imagem").style.display = 'flex'
    containerImagemCarregada.style.display = 'none'
    ocultarBotoes()
})

btnAtualizarFoto.addEventListener('click', () => formFoto.submit())

const getArquivoImagem = (input) => {
    const imagemCarregada = input.files[0]
    const file = new FileReader()
    file.addEventListener('load', () => {
        containerImagemCarregada.style.backgroundImage = `url(${file.result})`
        document.querySelector("#container-imagem").style.display = 'none'
        containerImagemCarregada.style.display = 'flex'
        mostrarBotes()
    })
    file.readAsDataURL(imagemCarregada)
}

uploadImagem.addEventListener('change', e => {
    getArquivoImagem(e.target)
})