package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.OPTION_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_CHILD;
import static seedu.address.logic.parser.CliSyntax.OPTION_CONTACT;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATE;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;



public class FindAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "findAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments with fields containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Options can be specified using" + PREFIX_OPTION + "<OPTION>" + " can be used to \n\n"
            + "Parameters: [" + PREFIX_OPTION + "<OPTION>]"
            + " KEYWORD [MORE_KEYWORDS]...\n"
            + "Options:\n"
            + " - " + OPTION_NAME + " (to find by name)\n"
            + " - " + OPTION_CHILD + " (to find by child)\n"
            + " - " + OPTION_ADDRESS + " (to find by address)\n"
            + " - " + OPTION_DATE + " (to find by date)\n"
            + " - " + OPTION_CONTACT + " (to find by contacts)\n"
            + "\n"
            + "Examples:\n"
            + " - " + COMMAND_WORD + " lunch meeting ptm\n"
            + " - " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_NAME + "\n"
            + " - " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_DATE + "20/05";

    private final Predicate<Appointment> predicate;

    /**
     * @param predicate Predicate to find the {@code Appointment} by.
     */
    public FindAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                        model.getFilteredAppointmentList().size()));

    }
}
