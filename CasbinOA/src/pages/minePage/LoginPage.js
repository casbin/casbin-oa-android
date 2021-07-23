import * as React from 'react';
import WebView from 'react-native-webview';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default class LoginPage extends React.Component {
  constructor(props) {
    super(props);
  }

  _signInAsync = async token => {
    console.log(token);
    await AsyncStorage.setItem('userToken', token.toString());
    this.props.navigation.navigate('Mine');
  };

  render() {
    const baseUrl = 'https://door.casbin.com';
    const client_id = '0ba528121ea87b3eb54d';
    const response_type = 'code';
    const redirect_uri = 'http://localhost:9000/callback';
    const scope = 'read';
    const state = 'casdoor';

    const HandleWebChange = async e => {
      let url = new URL(e.url);
      if (e && e.url && url.searchParams) {
        const Str = String(url);
        let ss = Str.split('?')[1].split('&');
        if (ss[0].split('=')[0] === 'code') {
          const code = ss[0].split('=')[1];

          const client_secret = '04f4ca22101529a3503d5a653a877b4e8403edf0';

          const body = new FormData();
          body.append('grant_type', 'authorization_code');
          body.append('client_id', client_id);
          body.append('client_secret', client_secret);
          body.append('code', code);

          fetch(`${baseUrl}/api/login/oauth/access_token`, {
            method: 'POST',
            headers: {
              'Content-Type': 'multipart/form-data',
            },
            body: body,
          })
            .then(res => res.json())
            .then(data => {
              this._signInAsync(data.access_token);
            })
            .catch(error => {})
            .finally(() => {});
        }
      }
    };

    const uri =
      `${baseUrl}/login/oauth/authorize?` +
      `client_id=${client_id}&` +
      `response_type=${response_type}&` +
      `redirect_uri=${redirect_uri}&` +
      `scope=${scope}&` +
      `state=${state}`;

    return (
      <WebView
        source={{
          uri: uri,
        }}
        onNavigationStateChange={HandleWebChange}
      />
    );
  }
}
