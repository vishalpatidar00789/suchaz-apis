/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { UserProfileSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz-detail.component';
import { UserProfileSuchazService } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz.service';
import { UserProfileSuchaz } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz.model';

describe('Component Tests', () => {

    describe('UserProfileSuchaz Management Detail Component', () => {
        let comp: UserProfileSuchazDetailComponent;
        let fixture: ComponentFixture<UserProfileSuchazDetailComponent>;
        let service: UserProfileSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [UserProfileSuchazDetailComponent],
                providers: [
                    UserProfileSuchazService
                ]
            })
            .overrideTemplate(UserProfileSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UserProfileSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.userProfile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
