package day4

class PassPhraseValidator {

    fun validate(words: List<String>): Boolean {
        for (i in 0 until words.size) {
            for (j in i + 1 until words.size) {
                if (words[i] == words[j]) {
                    return false
                }
            }
        }
        return true
    }

    fun validateWithAnagrams(words: List<String>): Boolean {
        for (i in 0 until words.size) {
            for (j in i + 1 until words.size) {
                if (words[i].toCharArray().sorted() == words[j].toCharArray().sorted()) {
                    return false
                }
            }
        }
        return true
    }


}