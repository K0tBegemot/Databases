import React from "react";
import PropTypes from "prop-types";

class SendButton extends React.Component
{

    render() {
        return (
            <div className={""}>
                <button type={"button"} className={"btn btn-dark text-light"} onClick={this.props.sendForm}>Send</button>
            </div>
        );
    }
}

SendButton.propTypes = {
    sendForm : PropTypes.func.isRequired,
}

export default SendButton;