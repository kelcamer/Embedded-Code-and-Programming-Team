/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  FacadePokemonCenter.java
 * Date: April 1, 2016
 */
public class Pokemon 
{
	//This class shall be used to represent Pokemon as data.
	//Pokemon aficionados will note the bare nature of this class and the paucity of given stats.
	//However, for our purposes, the given information will work just fine.
	
	//For consistency and ease of coding, this class was completed for you.
	//In the immortal words of the Bluth family, that was a freebie.
	
	//For our purposes, these values will represent all known Pokemon.
	private int pokedexNumber;
	private int percentHealth;
	private String pokemonName;
	
	//Constructor sets Pokedex number, sets name based on that number, and generates random health for it.
	public Pokemon(int dexNum)
	{
		pokedexNumber = dexNum;
		percentHealth = (int) Math.floor(Math.random() * 101);
		pokemonName = pokedex[dexNum];
	}
	
	//Returns the Pokemon's Pokedex number.
	public int getPokedexNumber()
	{
		return pokedexNumber;
	}
	
	//Returns the Pokemon's health as a percent.
	public int getPercentHealth()
	{
		return percentHealth;
	}
	
	//Returns the Pokemon's name.
	public String getPokemonName()
	{
		return pokemonName;
	}
	
	//Restores a Pokemon to full health.
	public void heal()
	{
		percentHealth = 100;
	}
	
	//String array, storing names of Pokemon based on their Pokedex number.
	static String[] pokedex =
	{
			 "MissingNo.", "Bulbasaur",  "Ivysaur",  "Venusaur",  "Charmander",  "Charmeleon",  "Charizard", 
			 "Squirtle",  "Wartortle",  "Blastoise",  "Caterpie",  "Metapod",  "Butterfree", 
			 "Weedle",  "Kakuna",  "Beedrill",  "Pidgey",  "Pidgeotto",  "Pidgeot", 
			 "Rattata",  "Raticate",  "Spearow",  "Fearow",  "Ekans",  "Arbok", 
			 "Pikachu",  "Raichu",  "Sandshrew",  "Sandslash",  "NidoranF",  "Nidorina", 
			 "Nidoqueen",  "NidoranM",  "Nidorino",  "Nidoking",  "Clefairy",  "Clefable", 
			 "Vulpix",  "Ninetales",  "Jigglypuff",  "Wigglytuff",  "Zubat",  "Golbat", 
			 "Oddish",  "Gloom",  "Vileplume",  "Paras",  "Parasect",  "Venonat", 
			 "Venomoth",  "Diglett",  "Dugtrio",  "Meowth",  "Persian",  "Psyduck", 
			 "Golduck",  "Mankey",  "Primeape",  "Growlithe",  "Arcanine",  "Poliwag", 
			 "Poliwhirl",  "Poliwrath",  "Abra",  "Kadabra",  "Alakazam",  "Machop", 
			 "Machoke",  "Machamp",  "Bellsprout",  "Weepinbell",  "Victreebel",  "Tentacool", 
			 "Tentacruel",  "Geodude",  "Graveler",  "Golem",  "Ponyta",  "Rapidash", 
			 "Slowpoke",  "Slowbro",  "Magnemite",  "Magneton",  "Farfetch'd",  "Doduo", 
			 "Dodrio",  "Seel",  "Dewgong",  "Grimer",  "Muk",  "Shellder", 
			 "Cloyster",  "Gastly",  "Haunter",  "Gengar",  "Onix",  "Drowzee", 
			 "Hypno",  "Krabby",  "Kingler",  "Voltorb",  "Electrode",  "Exeggcute", 
			 "Exeggutor",  "Cubone",  "Marowak",  "Hitmonlee",  "Hitmonchan",  "Lickitung", 
			 "Koffing",  "Weezing",  "Rhyhorn",  "Rhydon",  "Chansey",  "Tangela", 
			 "Kangaskhan",  "Horsea",  "Seadra",  "Goldeen",  "Seaking",  "Staryu", 
			 "Starmie",  "Mr. Mime",  "Scyther",  "Jynx",  "Electabuzz",  "Magmar", 
			 "Pinsir",  "Tauros",  "Magikarp",  "Gyarados",  "Lapras",  "Ditto", 
			 "Eevee",  "Vaporeon",  "Jolteon",  "Flareon",  "Porygon",  "Omanyte", 
			 "Omastar",  "Kabuto",  "Kabutops",  "Aerodactyl",  "Snorlax",  "Articuno", 
			 "Zapdos",  "Moltres",  "Dratini",  "Dragonair",  "Dragonite",  "Mewtwo", 
			 "Mew",  "Chikorita",  "Bayleef",  "Meganium",  "Cyndaquil",  "Quilava", 
			 "Typhlosion",  "Totodile",  "Croconaw",  "Feraligatr",  "Sentret",  "Furret", 
			 "Hoothoot",  "Noctowl",  "Ledyba",  "Ledian",  "Spinarak",  "Ariados", 
			 "Crobat",  "Chinchou",  "Lanturn",  "Pichu",  "Cleffa",  "Igglybuff", 
			 "Togepi",  "Togetic",  "Natu",  "Xatu",  "Mareep",  "Flaaffy", 
			 "Ampharos",  "Bellossom",  "Marill",  "Azumarill",  "Sudowoodo",  "Politoed", 
			 "Hoppip",  "Skiploom",  "Jumpluff",  "Aipom",  "Sunkern",  "Sunflora", 
			 "Yanma",  "Wooper",  "Quagsire",  "Espeon",  "Umbreon",  "Murkrow", 
			 "Slowking",  "Misdreavus",  "Unown",  "Wobbuffet",  "Girafarig",  "Pineco", 
			 "Forretress",  "Dunsparce",  "Gligar",  "Steelix",  "Snubbull",  "Granbull", 
			 "Qwilfish",  "Scizor",  "Shuckle",  "Heracross",  "Sneasel",  "Teddiursa", 
			 "Ursaring",  "Slugma",  "Magcargo",  "Swinub",  "Piloswine",  "Corsola", 
			 "Remoraid",  "Octillery",  "Delibird",  "Mantine",  "Skarmory",  "Houndour", 
			 "Houndoom",  "Kingdra",  "Phanpy",  "Donphan",  "Porygon2",  "Stantler", 
			 "Smeargle",  "Tyrogue",  "Hitmontop",  "Smoochum",  "Elekid",  "Magby", 
			 "Miltank",  "Blissey",  "Raikou",  "Entei",  "Suicune",  "Larvitar", 
			 "Pupitar",  "Tyranitar",  "Lugia",  "Ho-Oh",  "Celebi",  "Treecko", 
			 "Grovyle",  "Sceptile",  "Torchic",  "Combusken",  "Blaziken",  "Mudkip", 
			 "Marshtomp",  "Swampert",  "Poochyena",  "Mightyena",  "Zigzagoon",  "Linoone", 
			 "Wurmple",  "Silcoon",  "Beautifly",  "Cascoon",  "Dustox",  "Lotad", 
			 "Lombre",  "Ludicolo",  "Seedot",  "Nuzleaf",  "Shiftry",  "Taillow", 
			 "Swellow",  "Wingull",  "Pelipper",  "Ralts",  "Kirlia",  "Gardevoir", 
			 "Surskit",  "Masquerain",  "Shroomish",  "Breloom",  "Slakoth",  "Vigoroth", 
			 "Slaking",  "Nincada",  "Ninjask",  "Shedinja",  "Whismur",  "Loudred", 
			 "Exploud",  "Makuhita",  "Hariyama",  "Azurill",  "Nosepass",  "Skitty", 
			 "Delcatty",  "Sableye",  "Mawile",  "Aron",  "Lairon",  "Aggron", 
			 "Meditite",  "Medicham",  "Electrike",  "Manectric",  "Plusle",  "Minun", 
			 "Volbeat",  "Illumise",  "Roselia",  "Gulpin",  "Swalot",  "Carvanha", 
			 "Sharpedo",  "Wailmer",  "Wailord",  "Numel",  "Camerupt",  "Torkoal", 
			 "Spoink",  "Grumpig",  "Spinda",  "Trapinch",  "Vibrava",  "Flygon", 
			 "Cacnea",  "Cacturne",  "Swablu",  "Altaria",  "Zangoose",  "Seviper", 
			 "Lunatone",  "Solrock",  "Barboach",  "Whiscash",  "Corphish",  "Crawdaunt", 
			 "Baltoy",  "Claydol",  "Lileep",  "Cradily",  "Anorith",  "Armaldo", 
			 "Feebas",  "Milotic",  "Castform",  "Kecleon",  "Shuppet",  "Banette", 
			 "Duskull",  "Dusclops",  "Tropius",  "Chimecho",  "Absol",  "Wynaut", 
			 "Snorunt",  "Glalie",  "Spheal",  "Sealeo",  "Walrein",  "Clamperl", 
			 "Huntail",  "Gorebyss",  "Relicanth",  "Luvdisc",  "Bagon",  "Shelgon", 
			 "Salamence",  "Beldum",  "Metang",  "Metagross",  "Regirock",  "Regice", 
			 "Registeel",  "Latias",  "Latios",  "Kyogre",  "Groudon",  "Rayquaza", 
			 "Jirachi",  "Deoxys",  "Turtwig",  "Grotle",  "Torterra",  "Chimchar", 
			 "Monferno",  "Infernape",  "Piplup",  "Prinplup",  "Empoleon",  "Starly", 
			 "Staravia",  "Staraptor",  "Bidoof",  "Bibarel",  "Kricketot",  "Kricketune", 
			 "Shinx",  "Luxio",  "Luxray",  "Budew",  "Roserade",  "Cranidos", 
			 "Rampardos",  "Shieldon",  "Bastiodon",  "Burmy",  "Wormadam",  "Mothim", 
			 "Combee",  "Vespiquen",  "Pachirisu",  "Buizel",  "Floatzel",  "Cherubi", 
			 "Cherrim",  "Shellos",  "Gastrodon",  "Ambipom",  "Drifloon",  "Drifblim", 
			 "Buneary",  "Lopunny",  "Mismagius",  "Honchkrow",  "Glameow",  "Purugly", 
			 "Chingling",  "Stunky",  "Skuntank",  "Bronzor",  "Bronzong",  "Bonsly", 
			 "Mime Jr.",  "Happiny",  "Chatot",  "Spiritomb",  "Gible",  "Gabite", 
			 "Garchomp",  "Munchlax",  "Riolu",  "Lucario",  "Hippopotas",  "Hippowdon", 
			 "Skorupi",  "Drapion",  "Croagunk",  "Toxicroak",  "Carnivine",  "Finneon", 
			 "Lumineon",  "Mantyke",  "Snover",  "Abomasnow",  "Weavile",  "Magnezone", 
			 "Lickilicky",  "Rhyperior",  "Tangrowth",  "Electivire",  "Magmortar",  "Togekiss", 
			 "Yanmega",  "Leafeon",  "Glaceon",  "Gliscor",  "Mamoswine",  "Porygon-Z", 
			 "Gallade",  "Probopass",  "Dusknoir",  "Froslass",  "Rotom",  "Uxie", 
			 "Mesprit",  "Azelf",  "Dialga",  "Palkia",  "Heatran",  "Regigigas", 
			 "Giratina",  "Cresselia",  "Phione",  "Manaphy",  "Darkrai",  "Shaymin", 
			 "Arceus",  "Victini",  "Snivy",  "Servine",  "Serperior",  "Tepig", 
			 "Pignite",  "Emboar",  "Oshawott",  "Dewott",  "Samurott",  "Patrat", 
			 "Watchog",  "Lillipup",  "Herdier",  "Stoutland",  "Purrloin",  "Liepard", 
			 "Pansage",  "Simisage",  "Pansear",  "Simisear",  "Panpour",  "Simipour", 
			 "Munna",  "Musharna",  "Pidove",  "Tranquill",  "Unfezant",  "Blitzle", 
			 "Zebstrika",  "Roggenrola",  "Boldore",  "Gigalith",  "Woobat",  "Swoobat", 
			 "Drilbur",  "Excadrill",  "Audino",  "Timburr",  "Gurdurr",  "Conkeldurr", 
			 "Tympole",  "Palpitoad",  "Seismitoad",  "Throh",  "Sawk",  "Sewaddle", 
			 "Swadloon",  "Leavanny",  "Venipede",  "Whirlipede",  "Scolipede",  "Cottonee", 
			 "Whimsicott",  "Petilil",  "Lilligant",  "Basculin",  "Sandile",  "Krokorok", 
			 "Krookodile",  "Darumaka",  "Darmanitan",  "Maractus",  "Dwebble",  "Crustle", 
			 "Scraggy",  "Scrafty",  "Sigilyph",  "Yamask",  "Cofagrigus",  "Tirtouga", 
			 "Carracosta",  "Archen",  "Archeops",  "Trubbish",  "Garbodor",  "Zorua", 
			 "Zoroark",  "Minccino",  "Cinccino",  "Gothita",  "Gothorita",  "Gothitelle", 
			 "Solosis",  "Duosion",  "Reuniclus",  "Ducklett",  "Swanna",  "Vanillite", 
			 "Vanillish",  "Vanilluxe",  "Deerling",  "Sawsbuck",  "Emolga",  "Karrablast", 
			 "Escavalier",  "Foongus",  "Amoonguss",  "Frillish",  "Jellicent",  "Alomomola", 
			 "Joltik",  "Galvantula",  "Ferroseed",  "Ferrothorn",  "Klink",  "Klang", 
			 "Klinklang",  "Tynamo",  "Eelektrik",  "Eelektross",  "Elgyem",  "Beheeyem", 
			 "Litwick",  "Lampent",  "Chandelure",  "Axew",  "Fraxure",  "Haxorus", 
			 "Cubchoo",  "Beartic",  "Cryogonal",  "Shelmet",  "Accelgor",  "Stunfisk", 
			 "Mienfoo",  "Mienshao",  "Druddigon",  "Golett",  "Golurk",  "Pawniard", 
			 "Bisharp",  "Bouffalant",  "Rufflet",  "Braviary",  "Vullaby",  "Mandibuzz", 
			 "Heatmor",  "Durant",  "Deino",  "Zweilous",  "Hydreigon",  "Larvesta", 
			 "Volcarona",  "Cobalion",  "Terrakion",  "Virizion",  "Tornadus",  "Thundurus", 
			 "Reshiram",  "Zekrom",  "Landorus",  "Kyurem",  "Keldeo",  "Meloetta", 
			 "Genesect",  "Chespin",  "Quilladin",  "Chesnaught",  "Fennekin",  "Braixen", 
			 "Delphox",  "Froakie",  "Frogadier",  "Greninja",  "Bunnelby",  "Diggersby", 
			 "Fletchling",  "Fletchinder",  "Talonflame",  "Scatterbug",  "Spewpa",  "Vivillon", 
			 "Litleo",  "Pyroar",  "Flab�b�",  "Floette",  "Florges",  "Skiddo", 
			 "Gogoat",  "Pancham",  "Pangoro",  "Furfrou",  "Espurr",  "Meowstic", 
			 "Honedge",  "Doublade",  "Aegislash",  "Spritzee",  "Aromatisse",  "Swirlix", 
			 "Slurpuff",  "Inkay",  "Malamar",  "Binacle",  "Barbaracle",  "Skrelp", 
			 "Dragalge",  "Clauncher",  "Clawitzer",  "Helioptile",  "Heliolisk",  "Tyrunt", 
			 "Tyrantrum",  "Amaura",  "Aurorus",  "Sylveon",  "Hawlucha",  "Dedenne", 
			 "Carbink",  "Goomy",  "Sliggoo",  "Goodra",  "Klefki",  "Phantump", 
			 "Trevenant",  "Pumpkaboo",  "Gourgeist",  "Bergmite",  "Avalugg",  "Noibat", 
			 "Noivern",  "Xerneas",  "Yveltal",  "Zygarde",  "Diancie",  "Hoopa", "Volcanion"
		};
	
}
