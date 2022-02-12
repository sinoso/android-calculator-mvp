package edu.nextstep.camp.calculator

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : AndroidViewTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun button0() {
        // when : '0' 버튼을 누르면
        onViewsClick(R.id.button0)

        // then : '0'이 보여야 한다.
        onViewMatchText(R.id.textView, "0")
    }

    @Test
    fun button1() {
        // when : '1' 버튼을 누르면
        onViewsClick(R.id.button1)

        // then : '1'이 보여야 한다.
        onViewMatchText(R.id.textView, "1")
    }

    @Test
    fun button2() {
        // when : '2' 버튼을 누르면
        onViewsClick(R.id.button2)

        // then : '2'이 보여야 한다.
        onViewMatchText(R.id.textView, "2")
    }

    @Test
    fun button3() {
        // when : '3' 버튼을 누르면
        onViewsClick(R.id.button3)

        // then : '3'이 보여야 한다.
        onViewMatchText(R.id.textView, "3")
    }

    @Test
    fun button4() {
        // when : '4' 버튼을 누르면
        onViewsClick(R.id.button4)

        // then : '4'가 보여야 한다.
        onViewMatchText(R.id.textView, "4")
    }

    @Test
    fun button5() {
        // when : '5' 버튼을 누르면
        onViewsClick(R.id.button5)

        // then : '5'가 보여야 한다.
        onViewMatchText(R.id.textView, "5")
    }

    @Test
    fun button6() {
        // when : '6' 버튼을 누르면
        onViewsClick(R.id.button6)

        // then : '6'이 보여야 한다.
        onViewMatchText(R.id.textView, "6")
    }

    @Test
    fun button7() {
        // when : '7' 버튼을 누르면
        onViewsClick(R.id.button7)

        // then : '7'이 보여야 한다.
        onViewMatchText(R.id.textView, "7")
    }

    @Test
    fun button8() {
        // when : '8' 버튼을 누르면
        onViewsClick(R.id.button8)

        // then : '8'이 보여야 한다.
        onViewMatchText(R.id.textView, "8")
    }

    @Test
    fun button9() {
        // when : '9' 버튼을 누르면
        onViewsClick(R.id.button9)

        // then : '9'가 보여야 한다.
        onViewMatchText(R.id.textView, "9")
    }

    // 입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.
    @Test
    fun buttonPressed1() {
        // when : -> 1 클릭 -> 1
        onViewsClick(R.id.button1)

        // then : '1'이 보여야 한다.
        onViewMatchText(R.id.textView, "1")
    }

    // 입력된 피연산자가 없을 때, 사용자가 피연산자 0 ~ 9 버튼을 누르면 화면에 해당 숫자가 화면에 보여야 한다.
    @Test
    fun buttonPress5_Plus_1() {
        // when : 5 + -> 1 클릭 -> 5 + 1
        onViewsClick(R.id.button5, R.id.buttonPlus, R.id.button1)

        // then : '5 + 1'이 보여야 한다.
        onViewMatchText(R.id.textView, "5 + 1")
    }

    // 입력된 피연산자가 있을 때, 기존 숫자 뒤에 해당 숫자가 화면에 보여야 한다. 예를 들면, 8이 입력되어 있을 때 9를 입력하면 89가 보여야 한다.
    @Test
    fun buttonPress8_9() {
        // when : 8 -> 9 클릭 -> 89
        onViewsClick(R.id.button8, R.id.button9)

        // then : '89'가 보여야 한다.
        onViewMatchText(R.id.textView, "89")
    }

    // 입력된 피연산자가 없을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
    @Test
    fun buttonPressOperator_noOperand() {
        // when : -> +, -, ×, ÷ 클릭 ->
        onViewsClick(R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide)

        // then : ''이 보여야 한다.
        onViewMatchText(R.id.textView, "")
    }

    // 입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다.
    @Test
    fun buttonPressOperator_hasOperand_1() {
        // when : 1 -> + 클릭 -> 1 +
        onViewsClick(R.id.button1, R.id.buttonPlus)

        // then : '1 +'가 보여야 한다.
        onViewMatchText(R.id.textView, "1 +")
    }

    // 입력된 피연산자가 있을 때, 사용자가 연산자 +, -, ×, ÷ 버튼을 누르면 해당 기호가 화면에 보여야 한다.
    @Test
    fun buttonPressOperator_hasOperand_2() {
        // when : 1 + -> - 클릭 -> 1 -
        onViewsClick(R.id.button1, R.id.buttonPlus, R.id.buttonMinus)

        // then : '1 -'가 보여야 한다.
        onViewMatchText(R.id.textView, "1 -")
    }

    // 입력된 수식이 없을 때, 사용자가 지우기 버튼을 누르면 화면에 아무런 변화가 없어야 한다.
    @Test
    fun buttonDelete_noExpression() {
        // when : -> 지우기 클릭 ->
        onViewsClick(R.id.buttonDelete)

        // then : ''이 보여야 한다.
        onViewMatchText(R.id.textView, "")
    }

    // 입력된 수식이 있을 때, 사용자가 지우기 버튼을 누르면 수식에 마지막으로 입력된 연산자 또는 피연산자가 지워져야 한다.
    @Test
    fun buttonDelete_hasExpression() {
        // when : 32 + 1 -> 지우기 클릭 -> 32 + -> 지우기 클릭 -> 32 -> 지우기 클릭 -> 3 -> 지우기 클릭 ->  -> 지우기 클릭 ->
        onViewsClick(R.id.button3, R.id.button2, R.id.buttonPlus, R.id.button1)

        // then : '32 +'가 보여야 한다.
        onViewsClick(R.id.buttonDelete)
        onViewMatchText(R.id.textView, "32 +")

        // then : '32'가 보여야 한다.
        onViewsClick(R.id.buttonDelete)
        onViewMatchText(R.id.textView, "32")

        // then : '3'이 보여야 한다.
        onViewsClick(R.id.buttonDelete)
        onViewMatchText(R.id.textView, "3")

        // then : ''이 보여야 한다.
        onViewsClick(R.id.buttonDelete)
        onViewMatchText(R.id.textView, "")
    }

    // 입력된 수신이 완전할 때, 사용자가 = 버튼을 누르면 입력된 수식의 결과가 화면에 보여야 한다.
    @Test
    fun buttonDelete_validExpression() {
        // when : 3 + 2 -> = 클릭 -> 5
        onViewsClick(R.id.button3, R.id.buttonPlus, R.id.button2, R.id.buttonEquals)

        // then : '5'가 보여야 한다.
        onViewMatchText(R.id.textView, "5")
    }
}