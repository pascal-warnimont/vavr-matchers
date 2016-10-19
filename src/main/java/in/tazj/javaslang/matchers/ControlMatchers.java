package in.tazj.javaslang.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import javaslang.control.Either;
import javaslang.control.Option;
import javaslang.control.Try;

/**
 * Provides Hamcrest matchers for types from Javaslang's control package.
 */
public class ControlMatchers {
  public static Matcher<Option> isDefined() {
    return new TypeSafeMatcher<Option>() {
      @Override
      protected boolean matchesSafely(Option option) {
        return option.isDefined();
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Optional value should be defined");
      }
    };
  }

  public static Matcher<Option> isEmpty() {
    return new TypeSafeMatcher<Option>() {
      @Override
      protected boolean matchesSafely(Option option) {
        return option.isEmpty();
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Optional value should not be defined");
      }
    };
  }

  public static Matcher<Try> isSuccess() {
    return new TypeSafeMatcher<Try>() {
      @Override
      protected boolean matchesSafely(Try aTry) {
        return aTry.isSuccess();
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Try should have succeeded");
      }
    };
  }

  public static Matcher<Try> isFailure() {
    return new TypeSafeMatcher<Try>() {
      @Override
      protected boolean matchesSafely(Try aTry) {
        return aTry.isFailure();
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Try should have failed");
      }
    };
  }

  /**
   * Provides a matcher for {@link Try} that matches a failure with a given exception type.
   *
   * @param clazz The expected exception type.
   */
  public static <E extends Throwable> Matcher<Try> hasFailedWith(Class<E> clazz) {
    return new TypeSafeMatcher<Try>() {
      @Override
      protected boolean matchesSafely(Try aTry) {
        if (aTry.isFailure()) {
          return (aTry.getCause().getClass().equals(clazz));
        }
        return false;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("Try should have failed with ").appendText(clazz.getName());
      }

      @Override
      public void describeMismatchSafely(Try aTry, Description mismatch) {
        mismatch.appendText("Failure type is ").appendText(aTry.getCause().getClass().getName())
            .appendText(" but expected ").appendText(clazz.getName());
      }
    };
  }

  public static Matcher<Either> isRight() {
    return new TypeSafeMatcher<Either>() {
      @Override
      protected boolean matchesSafely(Either either) {
        return either.isRight();
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("»Either« should contain a »Right« value");
      }
    };
  }

  public static Matcher<Either> isLeft() {
    return new TypeSafeMatcher<Either>() {
      @Override
      protected boolean matchesSafely(Either either) {
        return either.isLeft();
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("»Either« should contain a »Left« value");
      }
    };
  }
}
