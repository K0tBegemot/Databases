import React from "react";
import PropTypes from "prop-types";

class ValidTypeField extends React.Component
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
                <label htmlFor={`Valid${fieldName}Field-${formNumber}-${fieldNumber}`} className={"form-label"}>
                    {label}
                </label>
                <div className={"input-group"} id={`Valid${fieldName}Field-${formNumber}-${fieldNumber}`}>
                    <div className={"input-group-text"}>
                        <input className={"form-check-input"} type={"checkbox"} value={"false"} aria-label={label} name={fieldName} onChange={(event) => {changeValidity(event)}}/>
                    </div>
                    <input id={`ValidText${fieldName}Field-${formNumber}-${fieldNumber}`} type={"text"} className={"form-control"} name={fieldName} placeholder={placeholder} onChange={(event) => {changeForm(event)}} disabled={!isValid}/>
                </div>
            </>
        );
    }
}

ValidTypeField.propTypes = {
    formNumber : PropTypes.number.isRequired,
    fieldNumber : PropTypes.number.isRequired,
    changeForm : PropTypes.func.isRequired,
    changeValidity : PropTypes.func.isRequired,
    valid : PropTypes.bool.isRequired,
    nameField : PropTypes.string.isRequired,
    label : PropTypes.string.isRequired,
    placeholder : PropTypes.string.isRequired,
}

export default ValidTypeField;