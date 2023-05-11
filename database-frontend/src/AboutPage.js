import React from "react";

class AboutPage extends React.Component{
    // eslint-disable-next-line no-useless-constructor
    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <div className="container-fluid text-light">
                <h4>
                    Данный сайт создал Бакелев Константин Валерьевич, группа 20207(aka KotBegemot)
                </h4>
            </div>
        );
    }
}

export default AboutPage;