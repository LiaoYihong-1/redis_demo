import React from 'react';
import {Route,Routes} from 'react-router-dom';
import SignUp from "./register/register";
import VideoPlayer from "./video/videoplayer";
import LoginPage from "./login/login";
import ComplexGrid from "./login/login";
class App extends React.Component {
    render(){
        return(
            <div>
                <Routes>
                    <Route path="/" element={
                        <LoginPage/>
                    }/>>
                    <Route path="/videos" element={
                        <VideoPlayer/>
                    }/>>
                    <Route path={"/register"} element={
                       <SignUp/>
                    }/>
                </Routes>
            </div>
        )
    }
}
export default App;