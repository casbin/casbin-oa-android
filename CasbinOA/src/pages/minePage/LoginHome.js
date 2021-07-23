import * as React from 'react';
import {Button, Text, View} from 'react-native';

export default class LoginHome extends React.Component {
  render() {
    return (
      <View>
        <View style={{alignItems: 'center', justifyContent: 'center'}}>
          <Button
            title="Press To Login"
            onPress={() => {
              this.props.navigation.navigate('LoginPage');
            }}
          />
        </View>
      </View>
    );
  }
}
