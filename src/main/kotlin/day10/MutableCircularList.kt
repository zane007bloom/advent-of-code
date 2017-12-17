package day10

class MutableCircularList<E> : ArrayList<E>(), MutableList<E> {

    override fun get(index: Int): E {
        return super.get(calculateIndex(index))
    }

    private fun calculateIndex(index: Int): Int {
        val factor = index / size
        return index - (factor * size)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableCircularList<E> {
        val subList = MutableCircularList<E>()
        for (i in fromIndex..toIndex) {
            subList.add(this[calculateIndex(i)])
        }
        return subList
    }

    fun addAfter(index: Int, element: E) {
        super.add(calculateIndex(index) + 1, element)
    }

    fun replaceAll(startIndex: Int, replacement: Collection<E>) {
        for (i in 0 until replacement.size) {
            this[calculateIndex(i + startIndex)] = replacement.elementAt(i)
        }
    }

}