package day7

class TreeBuilder {

    fun build(input: List<List<String>>): Program? {
        var parents = mutableMapOf<String, String?>()
        var programs = mutableMapOf<String, Program>()
        for (line in input) {
            val name = line[0]

            if (!parents.containsKey(name)) {
                parents.put(name, null)
            }

            val weight = line[1].toInt()

            val children: MutableList<Program?> = mutableListOf()
            for (i in 2 until line.size) {
                val childName = line[i]
                if (!programs.containsKey(childName)) {
                    programs.put(childName, Program(childName, 0, null))
                }
                children.add(programs[childName])
                parents.put(childName, name)
            }

            if (programs.containsKey(name)) {
                val program = programs[name]
                if (program != null) {
                    program.weight = weight
                    program.children = children
                }

            } else {
                programs.put(name, Program(name, weight, children))
            }

        }
        for (entry in parents) {
            if (entry.value == null) {
                return programs[entry.key]
            }
        }
        return null
    }


}