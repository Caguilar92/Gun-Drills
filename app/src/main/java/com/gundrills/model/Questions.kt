package com.gundrills.model

object Questions {


    private val questions60Mortar = mutableListOf<QuestionsModel>(
            QuestionsModel(1, "Describe the 60mm mortar system", "The M224/M224A1 60-mm mortar is a muzzle-loaded, smooth-bore, high-angle-of-fire weapon that can be fired in the conventional or handheld mode. It can be drop-fired or trigger-fired and has four major components", 1, 62),
            QuestionsModel(2, "How many Soldiers make up the 60mm mortar squad?", "Three\nSquad Leader, Gunner, Ammunition Bearer", 1, 62),
            QuestionsModel(3, "What are the four major components of the 60mm mortar?", "•M225A1 60-MM Mortar Cannon\n" + "• M170A1 60-MM Mortar Mount (Bipod)\n" + "• M7A1, M8 and M8A1 Baseplates\n" + "• M67 or M64A1 Sight Unit", 1, 63),
            QuestionsModel(4, "What are the six safety checks for the 60mm mortar?", "1. The cannon is locked to the baseplate and the open end of the socket cap points in the direction of fire. The bipod should be connected to either the upper or lower saddle of the cannon.\n" +
                    "2. The cannon is locked on the collar by the locking knob.\n" +
                    "3. The locking nut is hand-tight.\n" +
                    "4. The cable is taut (M170 bipod only).\n" +
                    "5. The selector switch on the cannon is on drop-fire.\n" +
                    "6. Check for mask and overhead clearance.", 1, 68),
            QuestionsModel(5, "How many many mils is considered a small deflection change?", "20 to 60 mils", 1, 69),
            QuestionsModel(6, "How many mils is considered a small elevation change?", "35 to 90 mils", 1, 69),
            QuestionsModel(7, "How many many mils is considered a large deflection change?", "200 to 300 mils", 1, 69),
            QuestionsModel(8, "How many mils is considered a large elevation change?", "100 to 200 mils", 1, 69),
            QuestionsModel(9, "Ideally, how far should the aiming posts be placed out?", "50 to 100 meters", 1, 69),
            QuestionsModel(10, "What is a hang fire?", "A hangfire is caused by a round becoming lodged in the breach preventing the rounds\n" +
                    "primer from striking the firing pin at the time of firing. In most cases, the delay ranges from a split\n" +
                    "second to several minutes. Thus, a hangfire cannot be distinguished from a misfire", 1, 72),
            QuestionsModel(11, "What is a cook off?", "A cook off is a functioning of one or more of the explosive components of a round\n" +
                    "chambered in a hot weapon, initiated by the heat of the weapon.", 1, 72),
            QuestionsModel(12, "What is a short round?", "A short round occurs when there is incomplete firing of the propelling charge explosive\n" +
                    "train, causing the round to be discharged under significantly reduced pressure. To avoid erratic or\n" +
                    "short rounds ensure that all components are free of sand, mud, moisture, frost, snow, ice, or other\n" +
                    "foreign matter before loading cartridge into cannon.", 1, 72),
            QuestionsModel(13, "What are the two ways the 60mm mortar can fired in?", "Conventional or Handheld", 1, 72),
            QuestionsModel(14, "What is the rate of fire for the M720A1 HE round?", "30 RPM for four minutes/15 RPM sustained", 2, 99),
            QuestionsModel(15, "What is the min range for the 60mm mortar?", "70m", 3, 19),
            QuestionsModel(16, "What is the max range for the 60mm mortar?", "1340m HH and 3490 conventional", 3, 19),
    )

    private val questions81Mortar = mutableListOf<QuestionsModel>(
            QuestionsModel(1, "Describe the 81mm mortar system", "The M252/M252A1 81-mm mortar is a smooth-bore, muzzle-loaded, high angle-of-fire\n" +
                    "weapon. The components of the mortar consist of a cannon, mount, and baseplate. This section\n" +
                    "discusses the characteristics and nomenclature of each component", 1, 79),
            QuestionsModel(2, "How many Soldiers make up the 81mm mortar squad?", "The mortar squad consists of four Soldiers\n" +
                    "Squad leader.\n" +
                    " Gunner.\n" +
                    " Assistant gunner.\n" +
                    " Ammunition bearer.", 1, 78),
            QuestionsModel(3, "What are the four major components of the 81mm mortar?", "•M2253 81-MM Mortar Cannon\n" + "• M177/M177A1 81-MM Mortar Mount (Bipod)\n" + "• M3A1/A2 Baseplate\n" + "• M67 or M64A1 Sight Unit", 1, 80),
            QuestionsModel(4, "What are the five safety checks for the 81mm mortar?", "1. The cannon is locked to the baseplate and the open end of the socket cap points in the direction of fire.\n" +
                    " 2. Firing pin recess faces upwards.\n" +
                    "3. Bipod locking latch is locked, securing the cannon clamps.\n" +
                    "4. Leg-locking handwheel is hand tight\n" +
                    "5.Check for Mask and overhead clearance\n", 1, 85),
            QuestionsModel(5, "How many many mils is considered a small deflection change?", "20 to 60 mils", 1, 69),
            QuestionsModel(6, "How many mils is considered a small elevation change?", "35 to 90 mils", 1, 69),
            QuestionsModel(7, "How many many mils is considered a large deflection change?", "200 to 300 mils", 1, 69),
            QuestionsModel(8, "How many mils is considered a large elevation change?", "100 to 200 mils", 1, 69),
            QuestionsModel(9, "Ideally, how far should the aiming posts be placed out?", "50 to 100 meters", 1, 69),
            QuestionsModel(10, "What is a hang fire?", "A hangfire is caused by a round becoming lodged in the breach preventing the rounds\n" +
                    "primer from striking the firing pin at the time of firing. In most cases, the delay ranges from a split\n" +
                    "second to several minutes. Thus, a hangfire cannot be distinguished from a misfire", 1, 72),
            QuestionsModel(11, "What is a cook off?", "A cook off is a functioning of one or more of the explosive components of a round\n" +
                    "chambered in a hot weapon, initiated by the heat of the weapon.", 1, 72),
            QuestionsModel(12, "What is a short round?", "A short round occurs when there is incomplete firing of the propelling charge explosive\n" +
                    "train, causing the round to be discharged under significantly reduced pressure. To avoid erratic or\n" +
                    "short rounds ensure that all components are free of sand, mud, moisture, frost, snow, ice, or other\n" +
                    "foreign matter before loading cartridge into cannon.", 1, 72),
            QuestionsModel(13, "What is the rate of fire for the M821 HE round?", "30 RPM for two minutes/20 RPM sustained", 2, 100),
            QuestionsModel(14, "What is the min range for the 81mm mortar?", "80m", 3, 19),
            QuestionsModel(15, "What is the max range for the 81mm mortar?", "5790m", 3, 19),

            )

    // needs to be finished
    private val questions120Mortar = mutableListOf<QuestionsModel>(
            QuestionsModel(1, "Describe the 120mm mortar system", "The 120 mm mortar is a smooth-bore, muzzle-loaded, high angle-of-fire weapon. It consists of a cannon assembly, bipod assembly, and baseplate. The 120-mm mortar is\n" +
                    "designed to be employed in all phases and types of land warfare, and in all weather conditions.", 1, 93),

            QuestionsModel(2, "How many Soldiers make up the 120mm mortar squad?", "The mortar squad consists of four Soldiers\n" +
                    "Squad leader.\n" +
                    " Gunner.\n" +
                    " Assistant gunner.\n" +
                    " Ammunition bearer.", 1, 92),
            QuestionsModel(3, "What are the four major components of the 120mm mortar?", "•M298 120-MM Mortar Cannon\n" + "• M191 120-MM Mortar Mount (Bipod)\n" + "• M9A1 Baseplate\n" + "• M67 or M64A1 Sight Unit", 1, 94),
            QuestionsModel(4, "What are the six safety checks for the 120mm mortar?",
                    "1.Checks the firing pin to ensure that it is properly installed in the breech cap\n" +
                            " 2. Ensure the cannon is locked to the baseplate.\n" +
                            "3.Checks the spread cable to ensure that it is taut\n" +
                            "4. Checks the cross-leveling locking knob to ensure it is hand-tight\n" +
                            "5. Check the buffer housing assembly to ensure that it is locked.\n" +

                            "6. Checks for mask and overhead clearance.", 1, 100),
            QuestionsModel(5, "How many many mils is considered a small deflection change?", "20 to 60 mils", 1, 69),
            QuestionsModel(6, "How many mils is considered a small elevation change?", "35 to 90 mils", 1, 69),
            QuestionsModel(7, "How many many mils is considered a large deflection change?", "200 to 300 mils", 1, 69),
            QuestionsModel(8, "How many mils is considered a large elevation change?", "100 to 200 mils", 1, 69),
            QuestionsModel(9, "Ideally, how far should the aiming posts be placed out?", "50 to 100 meters", 1, 69),
            QuestionsModel(10, "What is a hang fire?", "A hangfire is caused by a round becoming lodged in the breach preventing the rounds\n" +
                    "primer from striking the firing pin at the time of firing. In most cases, the delay ranges from a split\n" +
                    "second to several minutes. Thus, a hangfire cannot be distinguished from a misfire", 1, 72),
            QuestionsModel(11, "What is a cook off?", "A cook off is a functioning of one or more of the explosive components of a round\n" +
                    "chambered in a hot weapon, initiated by the heat of the weapon.", 1, 72),
            QuestionsModel(12, "What is a short round?", "A short round occurs when there is incomplete firing of the propelling charge explosive\n" +
                    "train, causing the round to be discharged under significantly reduced pressure. To avoid erratic or\n" +
                    "short rounds ensure that all components are free of sand, mud, moisture, frost, snow, ice, or other\n" +
                    "foreign matter before loading cartridge into cannon.", 1, 72),
            QuestionsModel(13, "What is the M326 MSK?", "The M326 Mortar Stowage Kit is a powered device that allows a 120-mm mortar to be carried as a single unit on an M1101 trailer", 1, 105),
            QuestionsModel(14, "What is the M1064A3?", "The M106A3 carrier is an M113A3 armored\n" +
                    "personnel carrier modified to carry the 120-mm M121 mortar on a specially designed mount. It is\n" +
                    "fully tracked, highly mobile, and armor protected. It can be transported by air and is able to propel\n" +
                    "itself across water obstacles.", 1, 124),
            QuestionsModel(15, "What are the safety checks for the M1064 Mortar Carrier?", " 1. There is mask and overhead clearance.\n" +
                    "2. The breech assembly is locked in the turntable socket and the white line on the cannon\n" +
                    "bisects the white line on the buffer housing assembly.\n" +
                    "3. The M191 bipod assembly is locked to the turntable mount and the arrows on the mount\n" +
                    "handles are pointed down (vertical).\n" +
                    "4. The safety pins are installed.\n" +
                    "5. The buffer housing assembly is secured to the cannon by loosening the clamp handle\n" +
                    "assembly about a quarter of a turn, and tightening the clamp handle assembly until he hears a\n" +
                    "metallic click.\n" +
                    "6. The cross-leveling locking knob is hand-tightened.\n" +
                    "7. The bipod support assembly is locked in the high or low position and the safety pin is\n" +
                    "installed.\n" +
                    "8. The turntable is in the locked position.\n" +
                    "9. The cargo hatches are open and locked.\n" +
                    "10. The BAD locking knob is hand-tightened.", 1, 127),
            QuestionsModel(16, "What is the M1129A1?", " The M1129A1 is a mortar\n" +
                    "carrier for mounting the RMS6-L mortarStryker mortar\n" +
                    "the Stryker vehicle squads provide mobile, high-angle fire for close-in support of ground troops in complex\n" +
                    "terrain and urban environments. \n", 1, 132),
            QuestionsModel(17, "What are the components of the RMS6-L 120MM mortar?",
                    "1.Traverse Bearing\n" +
                            "2.Elevation Mechanism\n" +
                            "3.Folding Sleeve\n" +
                            "4.Cant Correct Knob\n" +
                            "5.Barrel\n" +
                            "6.Replenisher\n" +
                            "7.Sight Mount\n" +
                            "8.Elevation Handle\n" +
                            "9.Traversing Handle\n" +
                            "10.Recoil Mechanism\n" +
                            "11.Cradle Stop\n" +
                            "12.Traverse Quick Release", 1, 135),
            QuestionsModel(18, "What are some of the capabilities of the Stryker 120mm Mortar?", "The mortar for the Stryker mortar vehicle is a smooth bore, muzzle loaded, high angle of\n" +
                    "fire weapon that provides—\n" +
                    "1. A traverse field of 4400 mils.\n" +
                    "2. A maximum range of 6700 meters.\n" +
                    "3. A maximum angle of elevation of 1486 mils, enabling the mortar to engage targets\n" +
                    "effectively on reverse slopes or behind cover.\n" +
                    "4. Accurate firing on targets from 180 to 6700 meters.", 1, 139),
            QuestionsModel(19, "What is the rate of fire for the M933 HE round?", "16 RPM for one minute/4 RPM sustained", 2, 101),
            QuestionsModel(20, "What is the min range for the 120mm mortar(ground/track)?", "200m", 3, 19),
            QuestionsModel(21, "What is the max range for the 120mm mortar(ground/track)?", "7200m", 3, 19),
            QuestionsModel(22, "What is the min range for the 120mm mortar(RMS6-L)?", "200m", 3, 19),
            QuestionsModel(23, "What is the max range for the 120mm mortar(RMS6-L)?", "6570m", 3, 19),

            )


    fun getQestions(systemID: Int): MutableList<QuestionsModel> {
        var questionsList = mutableListOf<QuestionsModel>()
        when (systemID) {
            60 -> {
                questionsList = questions60Mortar.toMutableList()
            }
            81 -> {
                questionsList = questions81Mortar.toMutableList()
            }
            120 -> {
                questionsList = questions120Mortar.toMutableList()

            }
        }
        return questionsList
    }


}







