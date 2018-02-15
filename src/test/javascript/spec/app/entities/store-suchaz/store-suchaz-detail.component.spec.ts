/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { StoreSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/store-suchaz/store-suchaz-detail.component';
import { StoreSuchazService } from '../../../../../../main/webapp/app/entities/store-suchaz/store-suchaz.service';
import { StoreSuchaz } from '../../../../../../main/webapp/app/entities/store-suchaz/store-suchaz.model';

describe('Component Tests', () => {

    describe('StoreSuchaz Management Detail Component', () => {
        let comp: StoreSuchazDetailComponent;
        let fixture: ComponentFixture<StoreSuchazDetailComponent>;
        let service: StoreSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [StoreSuchazDetailComponent],
                providers: [
                    StoreSuchazService
                ]
            })
            .overrideTemplate(StoreSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StoreSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new StoreSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.store).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
