package com.rm.constiquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.util.Linkify
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var btnStart: Button
    lateinit var btnNext: Button
    lateinit var btnEnd: AppCompatButton
    lateinit var btnNextSection:Button
    lateinit var txtwait: TextView
    lateinit var parentLayout: RelativeLayout
    lateinit var rvQuiz:RecyclerView


    lateinit var llTop : LinearLayout
    lateinit var txtTitle : TextView
    lateinit var txtDes : TextView
    lateinit var txtLink : TextView
    lateinit var btnStudied : AppCompatButton

    lateinit var nsv : NestedScrollView




    private lateinit var questionAdapter: QuestionAdapter
    private val questionsList = mutableListOf<DataClassQuestion>()

    var doubleBackToExitPressedOnce = false


    var already1 = "nil"

    var already2 = "nil"

    var already3 = "nil"

    var already4 = "nil"

    var already5 = "nil"


    var a = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        btnNext = findViewById(R.id.btnNext)
        btnEnd = findViewById(R.id.btnEnd)
        btnNextSection = findViewById(R.id.btnNextSection)
        txtwait = findViewById(R.id.txtWait)
        parentLayout = findViewById(R.id.parentLayout)
        rvQuiz = findViewById(R.id.rvQuiz)

        llTop = findViewById(R.id.llTop)
        txtTitle = findViewById(R.id.txtTitle)
        txtDes = findViewById(R.id.txtDes)
        txtLink = findViewById(R.id.txtLink)
        btnStudied = findViewById(R.id.btnStudied)

        nsv = findViewById(R.id.nsv)



        // Set up RecyclerView
        rvQuiz.layoutManager = LinearLayoutManager(this)
        questionAdapter = QuestionAdapter(questionsList)
        rvQuiz.adapter = questionAdapter




        val text1 = "Make a random question for a quiz regarding Preamble of Indian constitution from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text2 = "Make a random question for a quiz regarding Fundamental Rights of Indian constitution from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text3 = "Make a random question for a quiz regarding Indian Executive from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text4 = "Make a random question for a quiz regarding Indian Judiciary from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "
        val text5 = "Make a random question for a quiz regarding Directive Principles of State Policy of Indian Constitution from your knowledge. Give four options, each option separated with #. Then give correct answer number 1 or 2 or 3 or 4 at the end after another #. Then give one or 2 sentence explanation for the answer after another #. Don't give anything extra, and don't make newline. Also don't use any apostrophe or double quotes anywhere." +
                " It should be in this format - 'question#option1#option2#option3#option4#answerNumber#explanation'. "



        val title1 = "Preamble of Indian Constitution"
        val des1 = "WE, THE PEOPLE OF INDIA, having solemnly resolved to constitute India into a SOVEREIGN SOCIALIST SECULAR DEMOCRATIC REPUBLIC and to secure to all its citizens:\n\n" +
                "JUSTICE, social, economic and political;\n" +
                "LIBERTY of thought, expression, belief, faith and worship;\n" +
                "EQUALITY of status and of opportunity;\n" +
                "and to promote among them all\n" +
                "FRATERNITY assuring the dignity of the individual and the unity and integrity of the Nation;\n" +
                "\nIN OUR CONSTITUENT ASSEMBLY this twenty-sixth day of November, 1949, do HEREBY ADOPT, ENACT AND GIVE TO OURSELVES THIS CONSTITUTION.\n"
        val link1 = "https://www.constitutionofindia.net/articles/preamble/"

        val title2 = "Fundamental Rights"
        val des2 = "Part III of the Constitution contains the Fundamental Rights guaranteed to Indian citizens, and in some cases all persons. They are classified into six categories, also including the remedies in case they are violated.\n" +
                "Fundamental rights are the basic human rights enshrined in the Constitution of India which are guaranteed to all citizens. They are applied without discrimination on the basis of race, religion, gender, etc. Significantly, fundamental rights are enforceable by the courts, subject to certain conditions.\n\n" +
                "There are six fundamental rights in the Indian Constitution. \n" +
                "\n1. Right to Equality (Articles 14 – 18)\n" +
                "The right to equality is one of the important fundamental rights of the Indian Constitution that guarantees equal rights for everyone, irrespective of religion, gender, caste, race or place of birth. It ensures equal employment opportunities in the government and insures against discrimination by the State in matters of employment on the basis of caste, religion, etc. This right also includes the abolition of titles as well as untouchability.\n\n" +
                "2. Right to Freedom (Articles 19 – 22)\n" +
                "Freedom is one of the most important ideals cherished by any democratic society. The Indian Constitution guarantees freedom to citizens. The freedom right includes many rights such as:\n" +
                "•\tFreedom of speech\n" +
                "•\tFreedom of expression\n" +
                "•\tFreedom of assembly without arms\n" +
                "•\tFreedom of association\n" +
                "•\tFreedom to practise any profession \n" +
                "•\tFreedom to reside in any part of the country\n" +
                "Some of these rights are subject to certain conditions of state security, public morality and decency and friendly relations with foreign countries. This means that the State has the right to impose reasonable restrictions on them.\n" +
                "\n3. Right against Exploitation (Articles 23 – 24)\n" +
                "This right implies the prohibition of traffic in human beings, begar, and other forms of forced labour. It also implies the prohibition of employment of children in factories, etc. The Constitution prohibits the employment of children under 14 years in hazardous conditions.\n" +
                "\n4. Right to Freedom of Religion (Articles 25 – 28)\n" +
                "This indicates the secular nature of Indian polity. There is equal respect given to all religions. There is freedom of conscience, profession, practice and propagation of religion. The State has no official religion. Every person has the right to freely practice his or her faith, and establish and maintain religious and charitable institutions.\n" +
                "\n5. Cultural and Educational Rights (Articles 29 – 30)\n" +
                "These rights protect the rights of religious, cultural and linguistic minorities, by facilitating them to preserve their heritage and culture. Educational rights are for ensuring education for everyone without any discrimination.\n" +
                "\n6. Right to Constitutional Remedies (32 – 35)\n" +
                "The Constitution guarantees remedies if citizens’ fundamental rights are violated. The government cannot infringe upon or curb anyone’s rights. When these rights are violated, the aggrieved party can approach the courts. Citizens can even go directly to the Supreme Court which can issue writs for enforcing fundamental rights.\n" +
                "\nWhy Right to Property is not a Fundamental Right?\n" +
                "There was one more fundamental right in the Indian Constitution, i.e., the right to property. \n" +
                "However, this right was removed from the list of fundamental rights by the 44th Constitutional Amendment. \n" +
                "This was because this right proved to be a hindrance towards attaining the goal of socialism and redistributing wealth (property) equitably among the people. \n"
        val link2 = "https://www.constitutionofindia.net/parts/part-iii/"

        val title3 = "Indian Executive"
        val des3 = "The Union Executive authorises powers to the government to implement laws that fall under Articles 52 to 78 of Chapter- I of Part IV. Due to Its important activities, the Union Executive became the most significant branch of the government. The union executive of India consists of the President, the head of the State, Vice-president and Prime Minister, and Council of Ministers who governs the Union and Attorney General. The Indian Constitution empowers the President’s executive authority but is not allowed to exercise it alone. The Prime Minister supervises the Council of Ministers helps the President exercise executive power.\n" +
                "\nThe union executive of India consists of three members to set laws and govern the administrative work within the parliament. The qualifications, as well as roles and powers of these executive members, are mentioned below:\n" +
                "\n1.The President\n" +
                "The President is the head of the state and the Union Executive who exercises executive powers under the supervision and direction of the Prime Minister and the Council of Ministers. All the executive powers were implemented in the name of the President.\n" +
                "\nQualification and duration to hold office\n" +
                "The President must be a citizen of India. The Article 56 says that the minimum age of the President should be thirty-five and he must be India’s citizen. Also, he has to be qualified as the elective member of the House of People. As per Article 56(1), The President can hold office for 5 years from when he take charge of his office from the first day. The office of the President can be empty in case of his resignation or impeachment and if he dies or is disqualified by the Supreme Court.\n" +
                "\nPower and Functions\n" +
                "Under Article 53, the President of the Indian Constitution can implement laws and order and is a policymaker. He can supervise the armed force as a Supreme Commander. He can address both Parliaments. He can appoint judges of both the Supreme and High Court and high officials of India. The President can announce war and peace under the prime minister’s direction and the Council of Ministers. He can summon as well as dissolve the parliament by his legislative powers The President also exercises special power in times of National as well as State and Financial emergencies.\n" +
                "\nThe Vice President\n" +
                "Under Articles 63 and 65, the Vice-President plays an important role in the vacancy in the President’s office. He must be the ex-chairperson of the Rajya Sabha. He can exercise the function of the President in his absence or any situation including his illness as well as death or resignation from the office.\n" +
                "\n\n2.The Prime Minister\n" +
                "Under Article 73, the Prime Minister is selected by the representatives of the people among the members of the Lok Sabha. He is the chief head of the Government and head of the Council of Ministers. He  advises the President.\n" +
                "\nQualification and duration to hold office\n" +
                "The Prime Minister has to be a member of  Rajya Sabha or Lok Sabha. He has to be twenty-five years old, in case he is the member of Lok Sabha or more than 30 years of age, if he is a Rajya Sabha candidate. He must be a citizen of India. He cannot be a part of any office that runs for profit under the Indian Government of any state. He can hold the office for indefinite time, but has to gain confidence of President and Lok Sabha. \n" +
                "\nPower and Function\n" +
                "The Prime Minister became the communication link between the parliament and the President. He is responsible for advising the President to distribute work under various ministries working for the Indian Government. The council of the union executives must give all the information regarding Union affairs or decisions taken for the Cabinet meetings’ administration.\n" +
                "\n\n3.Attorney General\n" +
                "The Attorney General advises the Govt. of India on legal matters and represents this in front of the Supreme Court. They are nominated by the President on the recommendation of the Union Cabinet and serve at the President’s discretion. \n" +
                "\nQualification and duration\n" +
                "They has to be qualified for the role as a Judge in the Supreme Court. Thus they had to be a high court justice for 5 years, or an attorney for 10 years, or an outstanding jurist. There is no pre-decided duration for the Attorney General of India. \n" +
                "\nPower and Function\n" +
                "The Attorney General advises the Government on legal implications. They also carry out the President’s legal responsibilities. The Attorney General does have the right to attend in all Indian courts and to join in Parliament sessions, but do not vote. The Attorney General represents the Government in all Supreme Court proceedings.\n" +
                "\n\n4.The Council of Minister\n" +
                "Under Article 74, the council of the union executives is the constitutional body in parliament. The Council of Ministers belongs to 60 to 70 ministers, including The State Ministers as well as the Cabinet Ministers and Deputy Ministers.\n" +
                "\nQualification and duration to hold office\n" +
                "The Council of Ministers must qualify to be a member of either House of the Parliament. He must be a citizen of India. There is no duration specified for them, and the Prime Minister can ask any Minister to resign, and the President can appoint any Minister.\n" +
                "\nPower and Function\n" +
                "As the union executive of india consists of the Council of Ministers, they can exercise combined power with the Legislative Assemblies. The true power in the Country is held by the Council of Ministers. Ministers, rather than the Governor, take maximum decisions in the cabinet.\n"
        val link3 = "https://knowindia.india.gov.in/profile/the-union/executive.php"

        val title4 = "Indian Judiciary"
        val des4 = "The Judiciary is the third organ of the government of India. It is the branch that interprets the law, settles disputes and administers justice to the citizens of India. The Judiciary in India is considered to be the watchdog of democracy. Additionally, the Indian judicial system is the Guardian of the Constitution of India.\n" +
                "\nIndependent Judiciary in India\n" +
                "The Indian judicial system must stay an independent and impartial structure. Independence means that the other two branches of the government don’t interfere with the working of the judicial system. The legislature and the executive branches can’t involve themselves in the functioning of the Judiciary in India.\n" +
                "The other two organs respect the decisions made by the Judiciary in India. It also means that the judges can perform their duties without favouring anyone.\n" +
                "Even though the Judiciary in India is independent, it’s not an arbitrary structure. It is a part of the democratic political structure of India. Therefore it is an accountable body. Judiciary in India is answerable to the Constitution of India and the people of India.\n" +
                "\nStructure of the Indian Judicial System\n" +
                "The Judiciary in India is a single integrated judicial system. It is a pyramid-like structure with the Supreme Court of India right at the top. After which, the High Courts follow and then, the District and in the end, are the subordinate courts. The lower courts function under the direct superintendence of the courts above them.\n" +
                "\nHere is the structure of the Judiciary:\n\n" +
                "•\tSupreme Court of India\n" +
                "It is the highest court in the Indian judicial system, established as the Part V of the Indian Constitution. The decisions made by the Supreme Court are binding to all the subordinate courts. Additionally, it can transfer judges of the High Courts. Supreme courts can move cases from the other courts to themselves. Lastly, it can transfer a matter from one High Court to another.\n" +
                "\n•\tHigh Court of India\n" +
                "This is the second most important court in the Indian judicial system. It is established according to Article 141 of the Constitution. The High Court can hear appeals from the lower courts and issue writs for Fundamental Rights. Additionally, the High Court can deal with the cases within the jurisdiction of the State. High Courts have the power to exercise superintendence and control over the courts below them.\n" +
                "\n•\tDistrict Courts\n" +
                "The District Courts are the following most essential courts in the Indian judicial system. These courts deal with the cases which arise in the District. District courts consider appeals on decisions which the lower courts give. Additionally, the district courts also decide on matters which involve serious criminal offences.\n" +
                "\n•\tSubordinate Courts\n" +
                "These are the last courts in the structure of the Indian judicial system. They take up cases that are civil and criminal.\n" +
                "\n\nApart from the different types of courts above, there are two more branches of the legal system in India. Here are the two branches of the judicial system in India:\n" +
                "1.\tCriminal Law deals with cases when a citizen or an entity commits a crime. A point is registered when the local police file a crime report. The court finally gives the verdict on the criminal cases on the matter.\n" +
                "2.\tCivil Law: It deals with the cases when there is a dispute over the violation of the Fundamental Rights of a citizen.\n" +
                "\n\nRole of the Judiciary\n\n" +
                "The Judiciary in India has many functions like:\n" +
                "1.\tAdministration of justice: The primary function of the Judiciary in India is to apply the law to specific cases where it needs to settle disputes. When any dispute is brought before the courts, its function is to determine the facts through evidence presented by the contesting people. The court then decides what law applies to the case and applies it. When the court finds someone found guilty of violating the law in the trial, the court will give a sentence on the penalty of the guilty person.\n" +
                "\n2.\tCreation of judge-case law: In cases, judges cannot select the appropriate direction that applies to the topic. They make decisions based on their wisdom and common sense. When these decisions happen, they build up a body of ‘judge-made law’ or ‘case law’. The previous decisions of the judges are generally regarded as binding later on for judges in similar cases.\n" +
                "\n3.\tGuardian of the Constitution: The Supreme Court is the guardian of the Constitution. The court decides any conflicts between the central government and the state governments. If a law or order violates any constitutional provision, the Supreme Court can declare it null and void.\n" +
                "\n4.\tProtector of Fundamental Rights: One of the main functions of the Judiciary is to make sure that people’s rights are not trampled upon by the State or anyone else. The Supreme Court enforces the Fundamental Rights by issuing writs.\n" +
                "\n\nJudges in the Judiciary\n" +
                "According to the Constitution, the Supreme Court can have 1 Chief Justice and 34 other judges. Besides these judges, there are High Court judges, Additional District & Session Judge, and Chief Judicial Magistrate.\n" +
                "The President of India appoints the Chief Justice and the 34 other judges. The Supreme Court can have a maximum strength of 34 judges.\n" +
                "Each High Court has a different number of judges. The President appoints the judges of the High Courts in the state.\n"
        val link4 = "https://en.wikipedia.org/wiki/Judiciary_of_India"

        val title5 = "Directive Principles of State Policy"
        val des5 = "The Directive Principles of State Policy are the directions the Indian government gives to its institutions to manage the country. The principles given out in Article 36-51 of the Indian Constitution are not enforced through any court. Still, they are deemed “fundamental” in the country’s government, making it the State’s responsibility to implement these principles in establishing laws to maintain a just society.Various landmark cases talk about their importance, and various skirmishes are also highlighted from time to time, thanks to judicial interpretation.\n" +
                "\nWhat do Directive Principles mean?\n" +
                "In 1945, the Sapru Committee proposed two kinds of individual rights. The first is justiciable rights, and the second is non-justiciable rights. The justiciable rights are known as Fundamental Rights, while the non-justiciable rights are known as  Directive Principles. The Directive Principles of State Policy are values incorporated without any legal sanctions. \n" +
                "The principles were influenced by Directive Principles relating to social fairness, economic well-being, foreign relations, and administrative and legal affairs as stated in the Irish Constitution. Directive Principles of State have several definitions, which are listed below:\n" +
                "They are a listed ‘instrument of instructions’ under the Government of the India Act, 1935.\n" +
                "They want the country to have economic and social democracy.\n" +
                "Directive Principles of State Policy are principles that are not constitutionally enforceable in court if they are broken. These values are based upon Gandhian ideology, which depicted Gandhi’s reconstruction agenda throughout the national struggle. These values also represent liberalism’s worldview.\n" +
                "\nImportance of Directive Principles\n" +
                "It highlights the country’s workers welfare, environmental conservation, rural development and growth, decentralisation of authority, a consistent civil code, and other factors that are deemed important while drafting legislation for a “welfare state.”\n" +
                "They give a set of rules for the government’s operation in the country, although they are non-justiciable.\n" +
                "\n\nClassification of The Directive Principles of State Policy\n" +
                "Although the Indian Constitution does not categorise Directive Principles, they are typically divided into three groups based on their substance and direction:\n" +
                "1.\tSocialist principles \n" +
                "2.\tGandhian principles\n" +
                "3.\tLiberal-intellectual principles.\n" +
                "\n\nCriticism of Directive Principles\n" +
                "The following are some of the grounds for the critique of the Directive Principles:\n" +
                "1.\tThis has no legal standing.\n" +
                "2.\tIt is arranged irrationally.\n" +
                "3.\tIts character is conservative.\n" +
                "4.\tIt may result in a constitutional clash between the federal government and the state.\n"
        val link5 = "https://knowindia.india.gov.in/profile/directive-principles-of-state-policy.php"



        txtTitle.text = title1
        txtDes.text = des1
        txtLink.text = link1

        Linkify.addLinks(txtLink, Linkify.WEB_URLS)
        txtLink.isClickable = true
        txtLink.isFocusable = true



        val sharedPreferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

// Initialize variables
        for (i in 1..5) {
            for (j in 1..3) {
                editor.putString("q${i}${j}", "0") // Example: q11, q12, ..., q53
            }
        }
        editor.putString("section", "1")  // Current section
        editor.putString("completed", "0") // Quiz completion status
        editor.apply()



        btnStudied.setOnClickListener {
            llTop.visibility = GONE
            btnStart.visibility = VISIBLE
            btnStart.text = "START"
            //btnNext.visibility = VISIBLE

        }


        btnStart.setOnClickListener {
            rvQuiz.visibility =VISIBLE
            btnStart.text = "NEXT"
            txtwait.visibility = VISIBLE
            //btnNext.visibility = VISIBLE
            var already = "xxx"
            var text = "yyy"
            if(a == 1){
                already = already1
                text = text1
            }
            else if(a == 2){
                already = already2
                text = text2
            }
            else if(a == 3){
                already = already3
                text = text3
            }
            else if(a == 4){
                already = already4
                text = text4
            }
            else if(a == 5){
                already = already5
                text = text5
            }

            val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
            val completed1 = sharedPreferences1.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
            if (completed1 == "1") {
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            }
            else if(already == "xxx" || text == "yyy"){
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
            }

            else{
                generateNewQuestion(already, text)
            }

        }


        btnNext.setOnClickListener {
            rvQuiz.visibility =VISIBLE
            txtwait.visibility = VISIBLE
            var already = "xxx"
            var text = "yyy"
            if(a == 1){
                already = already1
                text = text1
            }
            else if(a == 2){
                already = already2
                text = text2
            }
            else if(a == 3){
                already = already3
                text = text3
            }
            else if(a == 4){
                already = already4
                text = text4
            }
            else if(a == 5){
                already = already5
                text = text5
            }

            val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
            val completed1 = sharedPreferences1.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
            if (completed1 == "1") {
                val intent = Intent(this, Result::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            }
            else if(already == "xxx" || text == "yyy"){
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
            }

            else{
                generateNewQuestion(already, text)
            }

        }


        btnNextSection.setOnClickListener {



            val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)

            val completed1 = sharedPreferences1.getString("completed", "0") // Default to "0" if not found

// Check if the value of 'completed' is "1"
            if (completed1 == "1") {
                val intent = Intent(this, Result::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }
            else {
                a++
                // Increment the section value
                val currentSection = sharedPreferences1.getString("section", "1")!!.toInt() // Get current section
                val newSection = currentSection + 1 // Increment section
                val editor1 = sharedPreferences1.edit()
                editor1.putString("section", newSection.toString()) // Save the new section
                editor1.apply()

                btnNext.visibility = GONE
                btnStart.visibility = GONE
                llTop.visibility = VISIBLE


                if (newSection in 1..5) {
                    when (newSection) {
                        1 -> {
                            txtTitle.text = title1
                            txtDes.text = des1
                            txtLink.text = link1
                        }
                        2 -> {
                            txtTitle.text = title2
                            txtDes.text = des2
                            txtLink.text = link2
                        }
                        3 -> {
                            txtTitle.text = title3
                            txtDes.text = des3
                            txtLink.text = link3
                        }
                        4 -> {
                            txtTitle.text = title4
                            txtDes.text = des4
                            txtLink.text = link4
                        }
                        5 -> {
                            txtTitle.text = title5
                            txtDes.text = des5
                            txtLink.text = link5
                        }
                    }
                }


                Linkify.addLinks(txtLink, Linkify.WEB_URLS)
                txtLink.isClickable = true
                txtLink.isFocusable = true

                // Clear the list and update the adapter for the next section
                questionsList.clear() // Clear the list
                questionAdapter.notifyDataSetChanged()

                Toast.makeText(this, "Moving to Section $newSection!", Toast.LENGTH_SHORT).show()

                // Adjust button visibility
                //btnStart.visibility = VISIBLE
                btnNextSection.visibility = GONE
            }




        }


        btnEnd.setOnClickListener {





            val alterDialog = AlertDialog.Builder(this)
            alterDialog.setTitle("End Quiz")
            alterDialog.setMessage("Are you sure to end this quiz?")
            alterDialog.setPositiveButton("Submit") { _, _ ->



                val sharedPreferences1 = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
                val editor1 = sharedPreferences1.edit()
                editor1.putString("completed", "1") // Set completed to "1"
                editor1.apply()


                val intent = Intent(this, Result::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

            }
            alterDialog.setNegativeButton("No"){ _, _ ->


            }
            alterDialog.create()
            alterDialog.show()








        }


    }

    private fun generateNewQuestion(alreadyx : String, text :String) {
        var textx = text

        if(alreadyx !="nil"){
            textx = text + "These questions are already asked, so don't ask the following :" + alreadyx
        }

        Log.d("Quest", "Q: $textx")
        val textToGenerate = textx.replace("'", "").replace("\"", "")
        var output = ""
        var final = ""

        val task = GoogleCloudGenerativeLanguageTask(this) { result ->
            output = result

            try {
                val jsonObject = JSONObject(output)
                val candidatesArray = jsonObject.getJSONArray("candidates")
                if (candidatesArray.length() > 0) {
                    val firstCandidate = candidatesArray.getJSONObject(0)
                    val content = firstCandidate.getJSONObject("content")
                    val partsArray = content.getJSONArray("parts")

                    if (partsArray.length() > 0) {
                        val firstPart = partsArray.getJSONObject(0)
                        final = firstPart.getString("text")

                        val parts = final.split("#").map { it.trim() }

                        if (parts.size >= 7) {
                            val question = parts[0]
                            val option1 = parts[1]
                            val option2 = parts[2]
                            val option3 = parts[3]
                            val option4 = parts[4]
                            val answer = parts[5]
                            val explanation = parts[6]
                            // Log the question and options




                            if(alreadyx =="nil"){
                                if(a == 1) already1= question
                                else if(a == 2)  already2= question
                                else if(a == 3)  already3= question
                                else if(a == 4)  already4= question
                                else if(a == 5)  already5= question
                            }
                            else{
                                if(a == 1) already1= "$alreadyx, $question"
                                else if(a == 2)  already2= "$alreadyx, $question"
                                else if(a == 3)  already3= "$alreadyx, $question"
                                else if(a == 4)  already4= "$alreadyx, $question"
                                else if(a == 5)  already5= "$alreadyx, $question"
                            }



//                            already1  = "$alreadyx. $question"

                            Log.d("XYZ", "Question: $question")
                            Log.d("XYZ", "Option 1: $option1")
                            Log.d("XYZ", "Option 2: $option2")
                            Log.d("XYZ", "Option 3: $option3")
                            Log.d("XYZ", "Option 4: $option4")
                            Log.d("XYZ", "Answer : $answer")
                            Log.d("XYZ", "Explanation : $explanation")
                            // Add the new question to the list

                            val newQuestion = DataClassQuestion(question, option1, option2, option3, option4, answer, explanation)
                            questionsList.add(newQuestion)

                            // Notify the adapter about the new question
                            questionAdapter.notifyItemInserted(questionsList.size - 1)

                            // Smoothly scroll the NestedScrollView to the bottom
                            nsv.post {
                                nsv.smoothScrollTo(0, nsv.getChildAt(0).bottom)
                            }


                            if (questionsList.size >= 3) {
                                //a++ // Increment the value of `a`
//                                questionsList.clear() // Clear the list
//                                questionAdapter.notifyDataSetChanged() // Notify adapter about data change

                                val sharedPreferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE)
                                val currentSection = sharedPreferences.getString("section", "1")!!.toInt() // Get current section

                                if (currentSection == 5) {
                                    // Mark the quiz as completed
                                    val editor = sharedPreferences.edit()
                                    editor.putString("completed", "1") // Set completed to "1"
                                    editor.apply()

                                    btnNextSection.visibility = VISIBLE
                                    btnNextSection.setText("Get Results >")
                                    btnNext.visibility = GONE
                                    btnStart.visibility = GONE

                                    // Log all q values
//                                    for (i in 1..5) {
//                                        for (j in 1..3) {
//                                            val questionKey = "q${i}${j}" // Generate keys like q11, q12, ..., q53
//                                            val value = sharedPreferences.getString(questionKey, "0") // Default to "0" if not found
//                                            Log.d("QuizStatus", "Key: $questionKey, Value: $value")
//                                        }
//                                    }
                                    Toast.makeText(this, "All sections completed!", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Proceed to the next section

                                    btnNext.visibility = GONE
                                    btnStart.visibility = GONE
                                    btnNextSection.visibility  = VISIBLE
                                }





                            }
                            else{
                                btnNextSection.visibility  = GONE
                            }



                            //btnNext.visibility= VISIBLE
                            txtwait.visibility = GONE

                        } else {
                            Log.e("XYZ", "Failed to parse question and options correctly.")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("XYZ", "Error parsing JSON: ${e.message}")
                Toast.makeText(this, "Error parsing JSON!", Toast.LENGTH_SHORT).show()


                if(a == 1) already1= "nil"
                else if(a == 2)  already2=  "nil"
                else if(a == 3)  already3=  "nil"
                else if(a == 4)  already4=  "nil"
                else if(a == 5)  already5=  "nil"

            }
        }

        task.execute(textToGenerate)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            // If the back button is pressed twice, exit the activity
            super.onBackPressed()
            return
        }

        // If the back button is pressed for the first time, show a Toast
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Current quiz data will be lost. Click back again to exit.", Toast.LENGTH_SHORT).show()

        // Reset the flag after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000) // 2000ms = 2 seconds
    }
}
