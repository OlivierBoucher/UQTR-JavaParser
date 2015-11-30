package com.olivierboucher.inf1018.custom;

import com.olivierboucher.inf1018.JavaParserConstants;
import com.olivierboucher.inf1018.Token;

public class JavaToken extends Token
{
  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public JavaToken(int kind, String image)
  {
     this.kind = kind;
     this.image = image;
  }

  public int realKind = JavaParserConstants.GT;

  /**
   * Returns a new Token object.
  */

  public static final Token newToken(int ofKind, String tokenImage)
  {
    return new JavaToken(ofKind, tokenImage);
  }
}