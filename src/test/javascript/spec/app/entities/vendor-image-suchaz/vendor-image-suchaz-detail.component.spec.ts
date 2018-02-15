/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { VendorImageSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz-detail.component';
import { VendorImageSuchazService } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz.service';
import { VendorImageSuchaz } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz.model';

describe('Component Tests', () => {

    describe('VendorImageSuchaz Management Detail Component', () => {
        let comp: VendorImageSuchazDetailComponent;
        let fixture: ComponentFixture<VendorImageSuchazDetailComponent>;
        let service: VendorImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [VendorImageSuchazDetailComponent],
                providers: [
                    VendorImageSuchazService
                ]
            })
            .overrideTemplate(VendorImageSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VendorImageSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VendorImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new VendorImageSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.vendorImage).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
