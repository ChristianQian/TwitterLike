import React, { Component } from 'react';
import './form.css';
import RemoveButton from './RemoveButton';

export default class Flist extends Component{
	
render() {
    return (
      <div className="vc" ref="iScroll"
        style={{margin:"3px 3px 3px 3px", height: "window.innerHeight", overflowY: "auto" }}>
        		<div>
						{ this.props.l.map(u => {
							return	<RemoveButton kv={this.props.k2} u={u} ae={this.props.ae} m={this.props.m}/>				   
            })}
        	  </div>
      </div>
    );
} 
}