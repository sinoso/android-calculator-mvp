package edu.nextstep.camp.calculator

import com.github.dodobest.domain.Result
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class ResultAdapterTest {
    private lateinit var resultAdapter: ResultAdapter

    @Before
    fun setUp() {
        resultAdapter = ResultAdapter()
    }

    @Test
    fun `ResultAdapter에 Expression(3+3, 5)와 Expression(1+1, 2)를 add하면 ResultAdapter results list에 추가된다`() {
        // when
        resultAdapter.add("3+3", "5")
        resultAdapter.add("1+1", "2")
        val actual: List<Result> = resultAdapter.getResults()

        // then
        Truth.assertThat(actual).isEqualTo(arrayListOf(
            Result("3+3", "5")
            , Result("1+1", "2")
        ))
    }
}