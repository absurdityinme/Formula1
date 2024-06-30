package com.example.formula1

class AutoBuilder {

    private var brand: Brand = Brand.KAMAZ
    private var model: String = "JOJ-03"
    private var year: Int = 0
    private var typeOfEngine: Engine = Engine.PETROL
    private var typeOfDrive: Drive? = null
    private var enginePower: Int? = null
    private var autoPilot: YesNo? = null
    private var fuelConsumption: Int? = null
    private var loadCapacity: Int? = null
    private var fuelReserve: Int? = null
    private var halo: YesNo? = null
    private var maxSpeed: Int? = null
    private var mileage: Int? = null
    private var insurance: YesNo? = null

    fun setBrand(brand: Brand) = apply { this.brand = brand }
    fun setModel(model: String) = apply { this.model = model }
    fun setYear(year: Int) = apply { this.year = year }
    fun setTypeOfEngine(typeOfEngine: Engine) = apply { this.typeOfEngine = typeOfEngine }
    fun setTypeOfDrive(typeOfDrive: Drive?) = apply { this.typeOfDrive = typeOfDrive }
    fun setEnginePower(enginePower: Int?) = apply { this.enginePower = enginePower }
    fun setAutoPilot(autoPilot: YesNo?) = apply { this.autoPilot = autoPilot}
    fun setFuelConsumption(fuelConsumption: Int?) = apply { this.fuelConsumption = fuelConsumption}
    fun setLoadCapacity(loadCapacity: Int?) = apply { this.loadCapacity = loadCapacity}
    fun setFuelReserve(fuelReserve: Int?) = apply {this.fuelReserve = fuelReserve}
    fun setHalo(halo: YesNo?) = apply {this.halo = halo}
    fun setMaxSpeed(maxSpeed: Int?) = apply { this.maxSpeed = maxSpeed }
    fun setMileage(mileage: Int?) = apply { this.mileage = mileage }
    fun setInsurance(insurance: YesNo?) = apply {this.insurance = insurance}

    fun build() : Auto {
        return when {
            typeOfDrive != null && enginePower != null -> Crossover(brand, model, year, typeOfEngine, typeOfDrive!!, enginePower!!)
            autoPilot != null && fuelConsumption != null -> ModernCar(brand, model, year, typeOfEngine, autoPilot!!, fuelConsumption!!)
            loadCapacity != null && fuelReserve != null -> Truck(brand, model, year, typeOfEngine, loadCapacity!!, fuelReserve!!)
            halo != null && maxSpeed != null -> RaceCar(brand, model, year, typeOfEngine, halo!!, maxSpeed!!)
            mileage != null && insurance != null -> MileageCar(brand, model, year, typeOfEngine, mileage!!, insurance!!)
            else -> Auto(brand, model, year, typeOfEngine)
        }
    }
}