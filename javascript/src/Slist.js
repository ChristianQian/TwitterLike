import React, { Component } from 'react';
import './form.css';
import AddButton from './AddButton';

export default class Slist extends Component{

render() {
    return (
      <div className="vc" ref="iScroll"
        style={{margin:"3px 3px 3px 3px", height: "window.innerHeight", overflowY: "auto" }}>
        		<div>
						{ this.props.l.map(u => (
							<AddButton kv={this.props.k1} u={u} a={this.props.a} m={this.props.m}/>
						))}
        	  </div>
      </div>
    );
} 
}