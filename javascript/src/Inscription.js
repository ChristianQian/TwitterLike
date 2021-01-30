import React, { Component } from 'react';
import axios from 'axios';



class Inscription extends Component{
	constructor(props){
    super(props);
		this.state={ fn:"", ln:"", login:"", pw:"", apw:""};
		this.onChange = this.onChange.bind(this);
    }
    
    onChange = e => this.setState({ [e.target.name]: e.target.value })
    
    render(){
      return (
        <div className="inner-container">
			    <div className="Inscription">
          <div className="box">
              <div className="input-group">
              <input type='text' name="fn" autoComplete="off" value={this.state.fn} className="login-input" placeholder="Nom" onChange={this.onChange}/></div>
              <div className="input-group">
              <input type='text' name="ln" autoComplete="off" value={this.state.ln} className="login-input" placeholder="Prenom" onChange={this.onChange}/></div>
              <div className="input-group">
              <input type='text' name="login" autoComplete="off" value={this.state.login} className="login-input" placeholder="Login" onChange={this.onChange}/></div>
              <div className="input-group">
              <input type='password' name="pw" autoComplete="off" value={this.state.pw} className="login-input" placeholder="Password" onChange={this.onChange}/></div>
              <div className="input-group">
              <input type='password' name="apw" autoComplete="off" value={this.state.apw} className="login-input" placeholder="Confirm Password" onChange={this.onChange}/></div>
              <button type="button" className="login-btn" onClick={()=>this.send()}>Valider</button>
              <br/>
              <button type="button" className="login-btn" onClick={this.props.ws}>Annuler</button>
          </div>
          </div>
          </div>
        );
    }


    send(){
        if(this.state.fn!=="" && this.state.ln!=="" && this.state.login!=="" && this.state.pw !=="" && this.state.apw !=="" ){
          if( this.state.pw === this.state.apw){
            axios.get("http://localhost:8080/tme2/CreateUser?prenom="+this.state.ln+"&nom="+this.state.fn+"&login="+this.state.login+"&password="+this.state.pw).then(response=>{
              if(response.data.created){
                alert("Nouveau compte créé");
                this.props.ws();
              }else if(response.data.none)
                alert("Veuillez remplir tous les champs");
              else if(response.data.already)
                alert("login deja utilisé");
              else{
                alert("site en maintenance");
                this.props.ws();
              }
            }).catch(error=>{alert(error)})
          }else
            alert("Mots de passe non identiques")
        }
        else
          alert("Veuillez remplir tous les champs")
  } 
}
export default Inscription;
