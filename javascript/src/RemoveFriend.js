import React, { Component } from 'react';
import axios from 'axios';
import './form.css';

export default class RemoveFriend extends Component {
    constructor(props){
        super(props);
        this.state={login:""};
    }

    onChange = e => this.setState({ [e.target.name]: e.target.value });

    send(){
        if(this.state.login !==''){
            axios.get("http://localhost:8080/tme2/RemoveFriend?key="+this.props.k+"&login2="+this.state.login).then(response=>{
			if(response.data.none)
				alert("Veuillez saisir un login");
			else if(response.data.unknown)
				alert("Login non reconnu");
			else{
				alert("Ami retiré");
				this.props.c();
			}
			}).catch(response=>{alert(response)})           
        } else {
            alert("Login non autorisé")
        }
    }

    render(){
        return (
        <div className="RemoveFriend">
		    <div className="box">
                <div className="input-group">
			        <input type="text" name="login" className="login-input" placeholder='Username' value={this.state.login} onChange={this.onChange}/>
                    <button type="button" onClick={()=>this.send()}>Ajouté</button>
                </div>
            </div>
        </div>)
    }
}