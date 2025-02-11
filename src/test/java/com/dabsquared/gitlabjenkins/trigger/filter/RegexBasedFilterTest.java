package com.dabsquared.gitlabjenkins.trigger.filter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author Robin Müller
 */
@RunWith(Theories.class)
public class RegexBasedFilterTest {

	@DataPoints("matching-branches")
	public static String[] matchingBranchNames = {"feature/test", "feature/awesome-feature"};

	@DataPoints("not-matching-branches")
	public static String[] notMatchingBranchNames = {"hotfix/test", "hotfix/awesome-feature", "master", "develop"};

	@Theory
	public void isRegexBranchAllowed(@FromDataPoints("matching-branches") String branchName) {
		RegexBasedFilter featureBranches = new RegexBasedFilter(null, "feature/.*");

		assertThat(featureBranches.isBranchAllowed(null, branchName), is(true));
	}

	@Theory
	public void isRegexBranchNotAllowed(@FromDataPoints("not-matching-branches") String branchName) {
		RegexBasedFilter featureBranches = new RegexBasedFilter(null, "feature/.*");

		assertThat(featureBranches.isBranchAllowed(null, branchName), is(false));
	}
}
