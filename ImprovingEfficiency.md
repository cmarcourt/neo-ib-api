Several things have been improved from IB API to improve efficiency.

# Efficiency #
## Implementation of multi-threading ##
### IB API ###
One thread to send requests and place orders (must be synchronized). Only one thread to read from `InputStream` and dispatch to EWrapper callback methods.

### Neo IB API ###
One thread to send requests and place orders (must be synchronized). One consumer thread reading from `InputStream`. One thread pool publishing events. One thread pool notifying events to listeners.

## `StrBuilder` has replaced `StringBuffer` ##
### IB API ###
In EReader object, method readStr() uses a `StringBuffer` and instantiates a new one every time. One has to know this method is called thousand times a minute.
```
protected String readStr() throws IOException {
  StringBuffer buf = new StringBuffer();
  while (true) {
    byte c = m_dis.readByte();
    if (c == 0) {
      break;
    }
    buf.append((char) c);
  }

  String str = buf.toString();
  return str.length() == 0 ? null : str;
}
```

### Neo IB API ###
`StringBuffer` has been replaced with `StrBuilder` (I chose `StrBuilder` from commons-lang as there are shortcut methods) as there is only one consumer, no need to synchronized. Only one instance exists of `StrBuilder` (singleton), no new instantiation each read.
```
public static final String readString(final InputStream inputStream) {
  BUILDER.clear();
  while (true) {
    final byte c = readByte(inputStream);
    if (c == 0) {
      break;
    }
    BUILDER.append((char) c);
  }
  final String s = BUILDER.toString();
  return s.length() == 0 ? null : s;
}
```