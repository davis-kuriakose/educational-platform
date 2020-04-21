package com.educational.platform.administration.course;

import com.educational.platform.administration.course.approve.CreateCourseProposalCommand;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CourseProposalTest {

    @Test
    void approve_approvedStatus() {
        // given
        final CreateCourseProposalCommand createCourseProposalCommand = new CreateCourseProposalCommand(15);
        final CourseProposal proposal = new CourseProposal(createCourseProposalCommand);

        // when
        proposal.approve();

        // then
        assertThat(proposal)
                .hasFieldOrPropertyWithValue("status", CourseProposalStatus.APPROVED);
    }

    @Test
    void approve_courseProposalAlreadyApproved_courseProposalAlreadyApprovedException() {
        // given
        final CreateCourseProposalCommand createCourseProposalCommand = new CreateCourseProposalCommand(15);
        final CourseProposal proposal = new CourseProposal(createCourseProposalCommand);
        ReflectionTestUtils.setField(proposal, "id", 11);
        ReflectionTestUtils.setField(proposal, "status", CourseProposalStatus.APPROVED);

        // when
        final ThrowableAssert.ThrowingCallable sendToApprove = proposal::approve;

        // then
        assertThatExceptionOfType(CourseProposalAlreadyApprovedException.class).isThrownBy(sendToApprove);
    }

}