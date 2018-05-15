/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { SuchAzMenuSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz-detail.component';
import { SuchAzMenuSuchazService } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.service';
import { SuchAzMenuSuchaz } from '../../../../../../main/webapp/app/entities/such-az-menu-suchaz/such-az-menu-suchaz.model';

describe('Component Tests', () => {

    describe('SuchAzMenuSuchaz Management Detail Component', () => {
        let comp: SuchAzMenuSuchazDetailComponent;
        let fixture: ComponentFixture<SuchAzMenuSuchazDetailComponent>;
        let service: SuchAzMenuSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [SuchAzMenuSuchazDetailComponent],
                providers: [
                    SuchAzMenuSuchazService
                ]
            })
            .overrideTemplate(SuchAzMenuSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SuchAzMenuSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SuchAzMenuSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SuchAzMenuSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.suchAzMenu).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
