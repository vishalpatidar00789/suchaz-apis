/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { UserGiftWrapperSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz-detail.component';
import { UserGiftWrapperSuchazService } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.service';
import { UserGiftWrapperSuchaz } from '../../../../../../main/webapp/app/entities/user-gift-wrapper-suchaz/user-gift-wrapper-suchaz.model';

describe('Component Tests', () => {

    describe('UserGiftWrapperSuchaz Management Detail Component', () => {
        let comp: UserGiftWrapperSuchazDetailComponent;
        let fixture: ComponentFixture<UserGiftWrapperSuchazDetailComponent>;
        let service: UserGiftWrapperSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [UserGiftWrapperSuchazDetailComponent],
                providers: [
                    UserGiftWrapperSuchazService
                ]
            })
            .overrideTemplate(UserGiftWrapperSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserGiftWrapperSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserGiftWrapperSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UserGiftWrapperSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.userGiftWrapper).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
