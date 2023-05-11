import React from "react";
import {Button, Nav, NavItem} from "react-bootstrap";
import InitialPage from "./InitialPage";
import RequestsPage from "./RequestsPage";
import AboutPage from "./AboutPage";
import EditPage from "./EditPage";

class NavigationBar extends React.Component{
    constructor(props, context) {
        super(props, context);
    }

    render() {
        return (
            <>
            <nav>
                <div className="nav nav-pills bg-dark navbar-expand-lg bg-body-tertiary" id="nav-tab" role="tablist">
                    <button className="btn btn-dark text-light nav-item active" id="nav-home-tab" data-bs-toggle="tab"
                            data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home"
                            aria-selected="true">
                        <img src="logo.png" alt="Database_logo" width="50px" height="50px" className="d-inline-block align-text-top rounded-1"/>
                        Авиаредприятие 3826
                    </button>
                    <button className="btn btn-dark text-light nav-item" id="nav-requests-tab" data-bs-toggle="tab" data-bs-target="#nav-requests"
                            type="button" role="tab" aria-controls="nav-requests" aria-selected="false">Запросы
                    </button>
                    <button className="btn btn-dark text-light nav-item" id="nav-edit-tab" data-bs-toggle="tab" data-bs-target="#nav-edit"
                            type="button" role="tab" aria-controls="nav-edit" aria-selected="false">Редактирование и вставка
                    </button>
                    <button className="btn btn-dark text-light nav-item" id="nav-contact-tab" data-bs-toggle="tab" data-bs-target="#nav-contact"
                            type="button" role="tab" aria-controls="nav-contact" aria-selected="false">О компании
                    </button>
                </div>
            </nav>
        <div className="tab-content" id="nav-tabContent">
            <div className="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab"
                 tabIndex="0">
                <InitialPage></InitialPage>
            </div>
            <div className="tab-pane fade" id="nav-requests" role="tabpanel" aria-labelledby="nav-requests-tab"
                 tabIndex="0">
                <RequestsPage></RequestsPage>
            </div>
            <div className="tab-pane fade" id="nav-edit" role="tabpanel" aria-labelledby="nav-edit-tab"
                 tabIndex="0">
                <EditPage></EditPage>
            </div>
            <div className="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab" style={{ height : "100vh", width : "100vw", backgroundImage : "url('KotBegemot.jpg')", backgroundSize : "cover", paddingTop : "25vh"}}
                 tabIndex="0">
                <AboutPage></AboutPage>
            </div>
        </div>
            </>
    );
    }
}

export default NavigationBar