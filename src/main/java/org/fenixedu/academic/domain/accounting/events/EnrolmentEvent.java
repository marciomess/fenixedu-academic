package org.fenixedu.academic.domain.accounting.events;

import java.util.Collection;
import java.util.Set;

import org.fenixedu.academic.domain.EnrolmentEvaluation;
import org.fenixedu.academic.domain.Person;
import org.fenixedu.academic.domain.accounting.Account;
import org.fenixedu.academic.domain.accounting.AccountType;
import org.fenixedu.academic.domain.accounting.EntryType;
import org.fenixedu.academic.domain.accounting.EventType;
import org.fenixedu.academic.domain.accounting.PostingRule;
import org.fenixedu.academic.domain.administrativeOffice.AdministrativeOffice;
import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.util.Bundle;
import org.fenixedu.academic.util.LabelFormatter;

public abstract class EnrolmentEvent extends EnrolmentEvent_Base {
    
    protected EnrolmentEvent() {
        super();
    }

    protected void init(final AdministrativeOffice administrativeOffice, final EventType eventType, final Person person,
            final Collection<EnrolmentEvaluation> enrolmentEvaluations) {
        checkParameters(enrolmentEvaluations);
        addAll(enrolmentEvaluations);
        super.init(administrativeOffice, eventType, person);
    }

    protected abstract void addAll(final Collection<EnrolmentEvaluation> enrolmentEvaluations);
    protected abstract Set<EnrolmentEvaluation> getAllEnrolmentEvaluationsSet();

    private void checkParameters(final Collection<EnrolmentEvaluation> enrolmentEvaluations) {
        if (enrolmentEvaluations == null || enrolmentEvaluations.isEmpty()) {
            throw new DomainException(
                    "error.accounting.events.EnrolmentInSpecialSeasonEvaluationEvent.enrolmentEvaluations.cannot.be.null");
        }
    }

    @Override
    public LabelFormatter getDescription() {
        final LabelFormatter labelFormatter = super.getDescription();

        getDetailedDescription(labelFormatter);

        return labelFormatter;
    }

    private void getDetailedDescription(final LabelFormatter labelFormatter) {
        labelFormatter.appendLabel(" (").appendLabel(getEnrolmentsDescription()).appendLabel(")");
    }

    private String getEnrolmentsDescription() {
        final StringBuilder result = new StringBuilder();
        for (final EnrolmentEvaluation enrolmentEvaluation : getAllEnrolmentEvaluationsSet()) {
            result.append(enrolmentEvaluation.getEnrolment().getName().getContent()).append(", ");
        }

        if (result.toString().endsWith(", ")) {
            result.delete(result.length() - 2, result.length());
        }

        return result.toString();
    }

    @Override
    public LabelFormatter getDescriptionForEntryType(EntryType entryType) {
        final LabelFormatter labelFormatter = new LabelFormatter();

        labelFormatter.appendLabel(entryType.name(), Bundle.ENUMERATION);
        getDetailedDescription(labelFormatter);

        return labelFormatter;
    }

    @Override
    protected Account getFromAccount() {
        return getPerson().getAccountBy(AccountType.EXTERNAL);
    }

    @Override
    public Account getToAccount() {
        return getAdministrativeOffice().getUnit().getAccountBy(AccountType.INTERNAL);
    }

    @Override
    public PostingRule getPostingRule() {
        return getAdministrativeOffice().getServiceAgreementTemplate().findPostingRuleByEventTypeAndDate(getEventType(),
                getWhenOccured());
    }

}
