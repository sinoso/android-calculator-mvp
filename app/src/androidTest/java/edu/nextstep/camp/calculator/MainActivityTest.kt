package edu.nextstep.camp.calculator

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import edu.nextstep.camp.calculator.utils.ToastMatcher.Companion.isToast
import edu.nextstep.camp.domain.Operator
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Created By Malibin
 * on 7월 24, 2021
 */

class CalculatorActivityTest {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 최근_입력이_숫자가_아닐_때_숫자를_누르면_화면에_해당_숫자가_추가_된다() {
        // given: "3 +"
        onView(withId(R.id.button3)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        val expectedText = "3 + 1"

        // when
        onView(withId(R.id.button1)).perform(click())


        // then
        onView(withId(R.id.text_result)).check(matches(withText(expectedText)))
    }

    @Test
    fun 최근_입력이_숫자일_때_숫자를_누르면_기존_숫자_뒤에_입력_숫자가_붙어_나타난다() {
        // given: "1"
        onView(withId(R.id.button1)).perform(click())

        val expectedText = "12"

        // when
        onView(withId(R.id.button2)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(withText(expectedText)))
    }

    @Test
    fun 아무_것도_입력된_게_없을_때_지우기_버튼을_누르면_텍스트에_변화가_없어야_한다() {
        // when
        onView(withId(R.id.buttonDelete)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(withText("")))
    }

    @Test
    fun 입력된_수식이_있을때_지우기_버튼을_누르면_마지막으로_입력된_값이_지워져야_한다() {
        // given: "46 + 5"
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.button6)).perform(click())

        onView(withId(R.id.buttonPlus)).perform(click())

        onView(withId(R.id.button5)).perform(click())

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.text_result)).check(matches(withText("46 +")))

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.text_result)).check(matches(withText("46")))

        // when
        onView(withId(R.id.buttonDelete)).perform(click())
        // then
        onView(withId(R.id.text_result)).check(matches(withText("4")))
    }

    @Test
    fun 입력된_수식이_완전할_때_결과_버튼을_누르면_수식의_결과가_텍스트에_나타난다() {
        // given: "8 + 9"
        onView(withId(R.id.button8)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button9)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(withText("17")))
    }

    @Test
    fun 입력된_수식이_완전하지_않을_때_결과_버튼을_누르면_토스트_메시지가_나타난다() {
        // given: "9 +"
        onView(withId(R.id.button9)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())

        // then
        onView(withText("완성되지 않은 수식입니다.")).inRoot(isToast())
            .check(matches(isDisplayed()))
    }

    @Test
    fun `시계_버튼을_누르면_계산_기록_뷰가_보이고_수식_뷰는_보이지_않는다`() {
        // when
        onView(withId(R.id.buttonMemory)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun `시계_버튼을_누른_후_다시_누르면_기록_뷰는_보이지_않고_수식_뷰가_보인다`() {
        // when : 1번 누른 상태
        onView(withId(R.id.buttonMemory)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        // when : 2번 누른 상태
        onView(withId(R.id.buttonMemory)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun `완전한_수식을_계산한_뒤_시계_버튼을_누르면_해당_기록이_보인다`() {
        // given: "1 + 2"
        onView(withId(R.id.button1)).perform(click())
        onView(withId(R.id.buttonPlus)).perform(click())
        onView(withId(R.id.button2)).perform(click())

        // when
        onView(withId(R.id.buttonEquals)).perform(click())
        onView(withId(R.id.buttonMemory)).perform(click())

        // then
        onView(withText("1 + 2")).check(matches(isDisplayed()))
        onView(withText("=3")).check(matches(isDisplayed()))
    }
}

@RunWith(Parameterized::class)
class CalculatorActivityNumberParameterizedTest(
    @IdRes private val buttonResId: Int,
    private val expectedText: String,
) {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "버튼 {1}을 누르면 {1}이 보인다")
        fun buttonResourcesAndNumbers(): Collection<Array<Any>> {
            return listOf(
                arrayOf(R.id.button0, "0"),
                arrayOf(R.id.button1, "1"),
                arrayOf(R.id.button2, "2"),
                arrayOf(R.id.button3, "3"),
                arrayOf(R.id.button4, "4"),
                arrayOf(R.id.button5, "5"),
                arrayOf(R.id.button6, "6"),
                arrayOf(R.id.button7, "7"),
                arrayOf(R.id.button8, "8"),
                arrayOf(R.id.button9, "9"),
            )
        }
    }

    @Test
    fun 아무_것도_입력된_게_없을_때_숫자_버튼을_누르면_해당_값이_텍스트뷰에_보여야한다() {
        // when
        onView(withId(buttonResId)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(withText(expectedText)))
    }
}

@RunWith(Parameterized::class)
class CalculatorActivityOperatorParameterizedTest(
    @IdRes private val operatorButtonResId: Int,
    private val symbol: String,
) {
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "연산자 버튼 {1} 일때")
        fun buttonResourcesAndNumbers(): Collection<Array<Any>> {
            return listOf(
                arrayOf(R.id.buttonPlus, Operator.Plus.sign),
                arrayOf(R.id.buttonMinus, Operator.Minus.sign),
                arrayOf(R.id.buttonMultiply, Operator.Multiply.sign),
                arrayOf(R.id.buttonDivide, Operator.Divide.sign),
            )
        }
    }

    @Test
    fun 아무_것도_입력된_게_없을_때_연산자_버튼을_누르면_텍스트뷰에_아무_변화가_없어야_한다() {
        // when
        onView(withId(operatorButtonResId)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(withText("")))
    }

    @Test
    fun 입력된_숫자가_있을_때_연산자_버튼을_누르면_텍스트뷰에_해당_기호가_보여야_한다() {
        // given
        onView(withId(R.id.button7)).perform(click())

        // when
        onView(withId(operatorButtonResId)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(withText("7 $symbol")))
    }

    @Test
    fun 최근_입력이_연산자일_때_연산자_버튼을_누르면_텍스트뷰의_연산자가_누른_연산자로_바뀐다() {
        // given
        onView(withId(R.id.button7)).perform(click())
        onView(withId(R.id.buttonMultiply)).perform(click())

        // when
        onView(withId(operatorButtonResId)).perform(click())

        // then
        onView(withId(R.id.text_result)).check(matches(withText("7 $symbol")))
    }
}
