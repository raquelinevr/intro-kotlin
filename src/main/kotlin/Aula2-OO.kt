package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 10) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            1 -> {
                print("Digite o nome do cachorro: ")
                val nomeCachorro = readln()
                print("Digite a idade do cachorro: ")
                val idadeCachorro = readln()?.toInt() ?: 0
                val cachorro = Cachorro(idadeCachorro, Cor.PRETO)
                cachorro.nome = nomeCachorro ?: ""
                repositorioAnimal.adicionar(cachorro)
            }
            2 -> {
                print("Digite o nome do gato: ")
                val nomeGato = readln()
                print("Digite a idade do gato: ")
                val idadeGato = readln()?.toInt() ?: 0
                val gato = Gato(idadeGato, Cor.BRANCO)
                gato.nome = nomeGato ?: ""
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                print("Digite o nome do pássaro: ")
                val nomePassaro = readln()
                print("Digite a idade do pássaro: ")
                val idadePassaro = readln()?.toInt() ?: 0
                val passaro = Passaro(idadePassaro, Cor.AMARELO)
                passaro.nome = nomePassaro ?: ""
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                repositorioAnimal.listar()
            }
            5 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            6 -> {
                print("Digite o nome do animal que será removido: ")
                val nomeRemover = readlnOrNull() ?: ""
                repositorioAnimal.remover(nomeRemover)
            }
            7 -> {
                print("Digite a cor dos animais a serem listados: ")
                println("Animais de cor BRANCA: ")
                repositorioAnimal.listarAnimaisPorCor(Cor.BRANCO)
            }

            8 -> {
                print("Digite a idade dos animais a serem listados: ")
                println("Animais com a idade 10: ")
                repositorioAnimal.listarAnimaisPorIdade(10)
            }

            9 -> {
                
                print("Digite o nome completo da pessoa: ")
                val nomeCompleto = readln()
                print("Digite a idade da pessoa: ")
                val idadePessoa = readln()?.toInt() ?: 0
                val pessoa = Homem(idadePessoa, nomeCompleto ?: "")
                repositorioAnimal.adicionar(pessoa)
            }

        }

    }
}

enum class Cor{
    BRANCO, PRETO, AMARELO
}


abstract class Animal(var idade: Int, var cor: Cor) {
    open var nome: String = ""
        get() = "Animal: $field"
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos(): Int{
        return idade * 7
    }
}

class Cachorro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
}
class Gato(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: Cor) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

class Homem(idade: Int, var nomeCompleto: String) : Animal(idade, Cor.BRANCO) {
    override var nome: String = ""

    override fun emitirSom() {
        println("Som de humano")
    }

    override fun idadeEmAnosHumanos(): Int {
        return idade
    }
}

fun menu() {
    println("1 - Cachorro")
    println("2 - Gato")
    println("3 - Pássaro")
    println("4 - Listar Animais")
    println("5 - Emitir som")
    println("6 - Remover um animal ")
    println("111 - Sair")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun buscarNome(nome: String): Animal? {
        return animais.find {it.nome == nome }
    }

    fun remover(nomeRemover: String){
        val animalRemover = buscarNome(nomeRemover)
        if (animalRemover != null){
            animais.remove(animalRemover)
            print("Animal removido com sucesso: $animalRemover")
        } else{
            println("Animal não encontrado!")
        }
    }


    fun listarAnimaisPorCor(cor: Cor){
        val animalCor = animais.filter { it.cor == cor }
        if (animalCor.isEmpty()){
            println("Não foram encontrados animais com a cor informada. ")
        } else{
            println("Cor não encontrada. ")
        }
    }

    fun listarAnimaisPorIdade(idade: Int) {
        val animalIdade = animais.filter { it.idade == idade }
        if (animalIdade.isEmpty()) {
            println("Não foram encotrados animais com a idade $idade")
        } else{
            println("Idade não encontrada. ")
        }


    }
}
