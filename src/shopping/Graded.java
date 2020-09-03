//DO NOT MODIFY

package shopping;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Graded {
	String description();
	int marks();
}
