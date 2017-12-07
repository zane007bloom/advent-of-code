package day7

data class Program(val name: String, var weight: Int, var children: MutableList<Program?>?, var totalWeight: Int) {

    fun getTotal(): Int {
        if (totalWeight != -1) {
            return totalWeight
        }
        if (children == null || children!!.isEmpty()) {
            return weight
        }
        var sum = weight
        for (child in children!!) {
            sum += child?.getTotal() ?: 0
        }
        totalWeight = sum
        return sum
    }

    fun getUnbalancedNode(): Program {
        if (children == null || children!!.isEmpty()) {
            return this
        }
        var least = children!![0]?.totalWeight
        var leastChild = children!![0]
        val totals: MutableList<Int> = mutableListOf()
        for (child in children!!) {
            if (child!!.getTotal() < least!!) {
                least = child.getTotal()
                leastChild = child
            }
            totals.add(child.getTotal())
        }
        val diff = totals.max()!! - totals.min()!!
        if (diff == 0) {
            return this
        }
        return leastChild!!
    }
}