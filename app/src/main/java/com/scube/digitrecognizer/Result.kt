package com.scube.digitrecognizer

class Result(probes: FloatArray, timeCost: Long) {
    val number: Int
    val probability: Float
    val timeCost: Long

    companion object {
        private fun argMax(probs: FloatArray): Int {
            var maxIdx = -1
            var maxProb = 0.0f
            for (i in probs.indices) {
                if (probs[i] > maxProb) {
                    maxProb = probs[i]
                    maxIdx = i
                }
            }
            return maxIdx
        }
    }

    init {
        number = argMax(probes)
        probability = probes[number]
        this.timeCost = timeCost
    }
}