/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { VendorSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz-detail.component';
import { VendorSuchazService } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz.service';
import { VendorSuchaz } from '../../../../../../main/webapp/app/entities/vendor-suchaz/vendor-suchaz.model';

describe('Component Tests', () => {

    describe('VendorSuchaz Management Detail Component', () => {
        let comp: VendorSuchazDetailComponent;
        let fixture: ComponentFixture<VendorSuchazDetailComponent>;
        let service: VendorSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [VendorSuchazDetailComponent],
                providers: [
                    VendorSuchazService
                ]
            })
            .overrideTemplate(VendorSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VendorSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VendorSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new VendorSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.vendor).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
