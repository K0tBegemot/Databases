import React from "react";
import PropTypes from "prop-types";
import DatePicker from "react-widgets/DatePicker";

class ValidDatePicker extends React.Component
{
    render() {
        const formNumber = this.props.formNumber;
        const fieldNumber = this.props.fieldNumber;
        const changeForm = this.props.changeForm;
        const changeValidity = this.props.changeValidity;
        const isValid = this.props.valid;
        const fieldName = this.props.nameField;
        const label = this.props.label;
        const placeholder = this.props.placeholder;
        return (
            <>
                <label htmlFor={`ValidDate${fieldName}Field-${formNumber}-${fieldNumber}`} className={"form-label"}>
                    {label}
                </label>
                <div className={"input-group"} id={`ValidDate${fieldName}Field-${formNumber}-${fieldNumber}`}>
                    <div className={"input-group-text"}>
                        <input className={"form-check-input"} type={"checkbox"} value={"false"} aria-label={label} name={fieldName} onChange={(event) => {changeValidity(event)}}/>
                    </div>
                    <DatePicker defaultValue={new Date()} id={`StartDateField-${formNumber}-${fieldNumber}`} name={fieldName} onChange={(event) => {changeForm(event, fieldName)}} disabled={!isValid}>
                    </DatePicker>
                </div>
            </>
        );
    }
}

ValidDatePicker.propTypes = {
    formNumber : PropTypes.number.isRequired,
    fieldNumber : PropTypes.number.isRequired,
    changeForm : PropTypes.func.isRequired,
    changeValidity : PropTypes.func.isRequired,
    valid : PropTypes.bool.isRequired,
    nameField : PropTypes.string.isRequired,
    label : PropTypes.string.isRequired
}

export default ValidDatePicker;