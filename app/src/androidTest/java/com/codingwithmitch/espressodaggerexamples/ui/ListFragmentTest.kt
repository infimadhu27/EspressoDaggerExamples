package com.codingwithmitch.espressodaggerexamples.ui


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.codingwithmitch.espressodaggerexamples.R
import com.codingwithmitch.espressodaggerexamples.TestBaseApplication
import com.codingwithmitch.espressodaggerexamples.di.DaggerTestAppComponent
import com.codingwithmitch.espressodaggerexamples.di.TestAppComponent
import com.codingwithmitch.espressodaggerexamples.fragments.MockFragmentFactory
import com.codingwithmitch.espressodaggerexamples.ui.BlogPostListAdapter.*
import com.codingwithmitch.espressodaggerexamples.util.EspressoIdlingResourceRule
import com.codingwithmitch.espressodaggerexamples.util.JsonUtil
import com.codingwithmitch.espressodaggerexamples.util.RecyclerViewMatcher
import com.codingwithmitch.espressodaggerexamples.util.printLogD
import com.codingwithmitch.espressodaggerexamples.viewmodels.MainViewModelFactory
import io.mockk.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named


@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class ListFragmentTest{

    private val CLASS_NAME = "ListFragmentTest"

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var jsonUtil: JsonUtil

    @Inject
    @Named("blog_posts_data_filename")
    lateinit var blog_posts_filename: String

    lateinit var appComponent: TestAppComponent

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()


    @Before
    fun beforeTests(){

        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestBaseApplication

        appComponent = DaggerTestAppComponent.builder()
            .application(app)
            .build()

        appComponent.inject(this)
    }

    @Test
    fun test() {

        val uiCommunicationListener = mockk<UICommunicationListener>()
        every {
            uiCommunicationListener.showCategoriesMenu(allAny())
        } just runs
        val fragmentFactory = MockFragmentFactory(
            viewModelFactory,
            uiCommunicationListener
        )
        val scenario = launchFragmentInContainer<ListFragment>(
            factory = fragmentFactory
        )


        onView(listMatcher().atPosition(5))
            .check(matches(hasDescendant(withText("Mountains in Washington"))))


//        onView(listMatcher().atPosition(5))
//            .check(matches(hasDescendant(withText("Mountains in Washington"))))

//        val recyclerView = onView(withId(R.id.recycler_view))
//
//        recyclerView.check(matches(isDisplayed()))
//
//        recyclerView
//            .perform(
//                RecyclerViewActions.scrollToPosition<BlogPostViewHolder>(6)
//            )
//        onView(withText("Mountains in Washington")).check(matches(isDisplayed()))


    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.recycler_view)
    }
}

















