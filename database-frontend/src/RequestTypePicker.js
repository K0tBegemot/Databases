import React from "react";
import PropTypes from "prop-types";

class RequestTypePicker extends React.Component
{
    render() {
        const formNumber = this.props.formNumber;
        const fieldNumber = this.props.fieldNumber;
        const changeForm1 = this.props.changeForm;
        const fieldName = this.props.nameField;
        const label = this.props.label;
        const valid = this.props.valid;
        const currentValue = this.props.currentValue;
        return (
            <div>
                <div className={"input-group"}>
                    <div className={"input-group-text form-check"}>
                        <input className="form-check-input" type="radio" name={fieldName} aria-label={`RequestType${fieldName}Field-${formNumber}-${fieldNumber}-${valid}`} id={`RequestTypeCheckField-${formNumber}-${fieldNumber}-${valid}`} onChange={(event) =>{changeForm1(event, valid)}} checked={currentValue === valid}/>
                        <label id = {`RequestType${fieldName}Field-${formNumber}-${fieldNumber}-${valid}`} className={"form-label"}>{label}</label>
                    </div>
                </div>
            </div>
        );
    }
}

RequestTypePicker.propTypes = {
    formNumber : PropTypes.number.isRequired,
    fieldNumber : PropTypes.number.isRequired,
    nameField : PropTypes.string.isRequired,
    label : PropTypes.string.isRequired,
    valid : PropTypes.string.isRequired,
    currentValue : PropTypes.string.isRequired,
}

export default RequestTypePicker;