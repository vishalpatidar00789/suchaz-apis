/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { SuchAzUserSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz-detail.component';
import { SuchAzUserSuchazService } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz.service';
import { SuchAzUserSuchaz } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz/such-az-user-suchaz.model';

describe('Component Tests', () => {

    describe('SuchAzUserSuchaz Management Detail Component', () => {
        let comp: SuchAzUserSuchazDetailComponent;
        let fixture: ComponentFixture<SuchAzUserSuchazDetailComponent>;
        let service: SuchAzUserSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [SuchAzUserSuchazDetailComponent],
                providers: [
                    SuchAzUserSuchazService
                ]
            })
            .overrideTemplate(SuchAzUserSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SuchAzUserSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuchAzUserSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SuchAzUserSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.suchAzUser).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
