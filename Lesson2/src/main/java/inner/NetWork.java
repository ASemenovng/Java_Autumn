package inner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetWork {

  private final List<Member> members;

  public NetWork() {
    members = new ArrayList<>();
  }

  public Member enroll(String name) {
    Member newMember = new Member(name);
    members.add(newMember);
    return newMember;
  }

  @Override
  public String toString() {
    return "NetWork{" +
        "members=" + members +
        '}';
  }

  public class Member {

    private final String name;
    private final List<Member> friends;

    public Member(String name, List<Member> friends) {
      this.name = name;
      this.friends = friends;
    }

    public Member(String name) {
      this.name = name;
      friends = new ArrayList<>();
    }

    public void delete() {
      members.remove(this);
      //NetWork.this.members.remove(this); // redundant this
       //outer.members.remove(this);
    }

    public boolean belongsTo(NetWork netWork) {
      return NetWork.this == netWork; // == ??
    }

    @Override
    public String toString() {
      return "Member{" +
          "name='" + name + '\'' +
          ", friends=" + friends +
          '}';
    }
  }
}
