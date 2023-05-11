import React from "react";

class InitialPage extends React.Component{
    // eslint-disable-next-line no-useless-constructor
    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <div className="container-fluid text-light shadow" style={{backgroundImage : "url('background.jpg')", width : "1920px", height : "1080px", paddingTop : "25vh"}}>
                <h1 className="display-4 text-capitalize rounded">Добро пожаловать на Авиационное Предприятие 3826!</h1>
                <p>
                    Самые передовые технологии были собраны здесь, чтобы используя передовые достижения науки и техники создавать одни из самых лучших моделей самолётов
                </p>
                <h1 className="display-4 text-capitalize">Пристёгивайте ремни и поехали!</h1>
            </div>
        );
    }
}

export default InitialPage;