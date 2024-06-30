package com.example.formula1

import kotlin.random.Random

open class Auto(
    val brand: Brand,
    val model: String,
    val year: Int,
    val typeOfEngine: Engine,
): Comparable<Auto> {
    override fun toString(): String {
        return "$brand $model of $year with $typeOfEngine engine"
    }

    override fun compareTo(auto: Auto): Int = when (
            val valueComparison = typeOfEngine.ordinal.compareTo(auto.typeOfEngine.ordinal)) {
            0 -> year.compareTo(auto.year)
            else -> valueComparison
    }
}

object RandomEnumHelper {
    fun <T : Enum<T>> random(enumClass: Class<T>): T {
        val enumConstants = enumClass.enumConstants
        return enumClass.enumConstants[Random.nextInt(enumConstants.size)]
    }
}



enum class Brand {
    FORD,
    VOLKSWAGEN,
    NISSAN,
    FERRARI,
    KIA,
    TESLA,
    KAMAZ,
    MERCEDES,
    TOYOTA,
    CHEVROLET,
    HYUNDAI,
    MITSUBISHI
}

enum class Engine {
    DIESEL,
    PETROL,
    ELECTRIC,
    GAS
}

enum class Drive{
    REAR_WHEEL,
    ALL_WHEEL,
    FRONT_WHEEL
}

enum class YesNo { YES, NO }

class Crossover(
    brand: Brand,
    model: String,
    year: Int,
    typeOfEngine: Engine,
    val typeOfDrive: Drive,
    val enginePower: Int
) : Auto(
    brand, model, year, typeOfEngine
) {
    override fun toString(): String {
        return super.toString() + "$enginePower kW, $typeOfDrive drive"
    }
}

class ModernCar(
    brand: Brand,
    model: String,
    year: Int,
    typeOfEngine: Engine,
    val autoPilot: YesNo,
    val fuelConsumption: Int
) : Auto(
    brand, model, year, typeOfEngine
) {
    override fun toString(): String {
        return super.toString() + (if (autoPilot == YesNo.YES) ", autopilot" else ", no autopilot") +
                ", $fuelConsumption fuel consumption"
    }
}

class Truck(
    brand: Brand,
    model: String,
    year: Int,
    typeOfEngine: Engine,
    val loadCapacity: Int,
    val fuelReserve: Int
) : Auto(
    brand, model, year, typeOfEngine
) {
    override fun toString(): String {
        return super.toString() + ", $loadCapacity load capacity, $fuelReserve fuel reserve"
    }
}

class RaceCar(
    brand: Brand,
    model: String,
    year: Int,
    typeOfEngine: Engine,
    val halo: YesNo,
    val maxSpeed: Int
) : Auto(
    brand, model, year, typeOfEngine
) {
    override fun toString(): String {
        return super.toString() + (if (halo == YesNo.YES) ", halo" else ", no halo") + ", max speed: $maxSpeed"
    }
}

class MileageCar(
    brand: Brand,
    model: String,
    year: Int,
    typeOfEngine: Engine,
    var mileage: Int,
    var insurance: YesNo
): Auto(
    brand, model, year, typeOfEngine
) {
    override fun toString(): String {
        return super.toString() + ", $mileage mileage and " + if (insurance == YesNo.YES) "insurance" else "no insurance"
    }
}