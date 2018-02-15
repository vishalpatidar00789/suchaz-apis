/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { UserProfileSuchazComponent } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz.component';
import { UserProfileSuchazService } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz.service';
import { UserProfileSuchaz } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz.model';

describe('Component Tests', () => {

    describe('UserProfileSuchaz Management Component', () => {
        let comp: UserProfileSuchazComponent;
        let fixture: ComponentFixture<UserProfileSuchazComponent>;
        let service: UserProfileSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [UserProfileSuchazComponent],
                providers: [
                    UserProfileSuchazService
                ]
            })
            .overrideTemplate(UserProfileSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UserProfileSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.userProfiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
